public class BusEvent implements IEvent {
    private int curStop;
    private Bus bus;

    public BusEvent(int stop, Bus b) {
        this.curStop = stop;
        this.bus = b;
    }

    public void run() {
        // exiting will store all exiting riders
        Rider[] exiting = bus.unloadBusAtStop(curStop);
        // this will board riders and track how many for time tracking
        Stop thisStop = BusSim.stopArray[curStop];
        int boarding = thisStop.boardFromStop(bus);
        // time stopped will give the time taken for riders to exit/board the bus, or 15 seconds, whichever is greater
        int timeStopped = 15;
        int timeToChange;
        if (exiting == null) {
            timeToChange = boarding*3;
        }
        else {
            timeToChange = (exiting.length*2) + (boarding*3);
        }
        if (timeToChange>15) { timeStopped=timeToChange; }
        int nextStop = curStop+1;
        if (nextStop > 29) {
            nextStop = 0;
        }
        int[] expressStops = {0,1,14,15,16,29,4,8,12,20,24,28};
        if (bus.isExpress()) { // conditional for choosing the next stop for an express bus
            for (int i=0; i<4; i++) {
                for (int j=0; j<expressStops.length; j++) {
                    if (expressStops[j] == nextStop) {
                        break;
                    }
                }
                nextStop++;
                if (nextStop > 29) { nextStop = 0; }
            }
        }
//        if (exiting == null) {
//            System.out.println("Bus dropped off: 0 riders and picked up: "+boarding+
//                    " riders at stop: "+curStop+" at time: "+BusSim.agenda.getCurrentTime());
//        }
//        else {
//            System.out.println("Bus dropped off: " + exiting.length + " riders and picked up: " + boarding +
//                    " riders at stop: " +curStop+" at time: "+BusSim.agenda.getCurrentTime());
//        }
        int nextStopTime = timeStopped + 240 + (int) BusSim.agenda.getCurrentTime();
//        System.out.println("Next stop is: "+nextStop+" at time: "+nextStopTime);

        BusSim.agenda.add(new BusEvent(nextStop,bus), timeStopped+240);
    }
}
