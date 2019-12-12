import java.util.Random;

public class BusSim {
    public static PQ agenda = new PQ();
    public static Stop[] stopArray = new Stop[30];
    // rider time is the user requested average time between riders at each stop
    public static int riderTime = 80;
    public static int totalSimTime = 20000;
    public static int numBuses = 10;
    public static int numExpressBuses = 10;

    public static void main(String[] args) {

        for (int i=0; i<stopArray.length; i++) {
            stopArray[i] = new Stop();
            agenda.add(new Arrival(i), 0);
            if (i==0 || i==1 || i==14 || i==15 || i== 16 || i==29) {
                agenda.add(new Arrival(i), 20);
            }
            // System.out.println("Stop: "+i+" created");
        }

        for (int i=0; i<numBuses; i++) {
            double busDist = 30/(double) numBuses;
            busDist = busDist*i;
            int stop = (int) busDist;
            stop %= 30;
            //System.out.println(stop);
            agenda.add(new BusEvent(stop, new Bus(false)), 0);
        }
        for (int i=0; i<numExpressBuses; i++) {
            int[] expressStops = {0,1,14,15,16,29,4,8,12,20,24,28};
            double busDist = 12/(double) numExpressBuses;
            busDist = busDist*i;
            busDist %= 12;
            int stop = (int) busDist;
            agenda.add(new BusEvent(expressStops[stop], new Bus(true)), 0);
            //System.out.println("New standard bus created at stop: "+expressStops[busDist*i]);
        }
//        agenda.add(new BusEvent(0, new Bus(false)), 0);
//        agenda.add(new BusEvent(2, new Bus(false)), 0);
//        agenda.add(new BusEvent(4, new Bus(false)),0);
//        agenda.add(new BusEvent(6, new Bus(false)),0);
//        agenda.add(new BusEvent(8, new Bus(false)),0);
//        agenda.add(new BusEvent(10, new Bus(false)),0);
//        agenda.add(new BusEvent(12, new Bus(false)),0);
//        agenda.add(new BusEvent(14, new Bus(false)),0);
//        agenda.add(new BusEvent(16, new Bus(false)),0);
//        agenda.add(new BusEvent(18, new Bus(false)),0);
//        agenda.add(new BusEvent(20, new Bus(false)),0);
//        agenda.add(new BusEvent(22, new Bus(false)),0);
//        agenda.add(new BusEvent(24, new Bus(false)),0);
//        agenda.add(new BusEvent(26, new Bus(false)),0);
//        agenda.add(new BusEvent(28, new Bus(false)),0);
//        agenda.add(new BusEvent(29, new Bus(false)),0);

        while (agenda.getCurrentTime() <=totalSimTime) {
            agenda.remove().run();
        }
        Stats.getStats();
    }
}
