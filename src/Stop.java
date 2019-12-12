public class Stop {
    //  stopQ is the queue containing all riders waiting at this stop
    private Q<Rider> stopQ = new Q();
    // count keeps track of the number of people waiting at the stop
    private int count = 0;

    public Stop() {}

    public void addToStop(Rider m) {
        stopQ.add(m);
        count++;
    }

    // boardFromStop is used when a bus arrives at a given stop, and has conditions for loading
    // both a standard bus and an express bus, returns the number of riders boarding the bus
    public int boardFromStop(Bus bus) {
        Stats.addLine(count);
        int boarding = 0;
        // standard bus
        if (bus.isExpress() == false) {
            while (count>0 && bus.isFull() == false) {
                Rider toBoard = (Rider) stopQ.remove();
                toBoard.setBoardTime(BusSim.agenda.getCurrentTime());
                bus.addRider(toBoard);
                count--;
                boarding++;
            }
        }
        // express bus block, puts non-express riders into a temporary queue before putting them back
        // into the stop queue in their original order
        else {
            Q tempQ = new Q();
            int tempQCount = 0;
            while (count>0 && bus.isFull() == false) {
                Rider nextRider = (Rider) stopQ.remove();
                if (nextRider.getExpress()) {
                    bus.addRider(nextRider);
                    boarding++;
                }
                else {
                    tempQ.add(nextRider);
                    tempQCount++;
                }
                count--;
            }
            while (tempQCount > 0) {
                stopQ.add((Rider) tempQ.remove());
                tempQCount--;
                count++;
            }
        }
        return boarding;
    }
    public boolean isEmpty() {
        if (count == 0) {return true;}
        else {return false;}
    }
    public Rider testStopRemove() {
        return stopQ.remove();
    }
    public static void main(String[] args) {
        Rider rider0 = new Rider(0,0);
        Rider rider1 = new Rider(1,0);
        Stop stop0 = new Stop();
        stop0.addToStop(rider0);
        System.out.println(stop0.testStopRemove());
    }
}
