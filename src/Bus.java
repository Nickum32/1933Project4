public class Bus {
    // tracks whether this is an express bus or not
    private boolean express;
    // numRiders keeps track of the number of riders on the bus at any moment
    private int numRiders = 0;
    // riders stores the passengers currently on the bus
    private Rider[] riders = new Rider[50];

    public Bus(boolean express) {
        this.express = express;
        Stats.addBus(this);
    }

    public boolean addRider(Rider m) {
        if (numRiders < 50) {
            riders[numRiders] = m;
            numRiders++;
            return true;
        }
        return false;
    }

    public Rider[] unloadBusAtStop(int stop) {
        Stats.addBusData(numRiders);
        if (numRiders == 0) {return null; }
        else {
            // exiting is an array that will hold all riders getting off the bus
            Rider[] exiting = new Rider[numRiders];
            int exitCount = 0;
            // staying is an array that will temporarily hold the riders staying on the bus
            Rider[] staying = new Rider[50];
            int stayCount = 0;
            for (int i=0; i<numRiders; i++) {
                if (riders[i].getDropStop() == stop) {
                    exiting[exitCount] = riders[i];
                    exitCount ++;
                }
                else {
                    staying[stayCount] = riders[i];
                    stayCount ++;
                }
            }
            riders = staying;
            numRiders = stayCount;
            if (exitCount == 0) {return null;}
            // creating a properly sized array with no null values for a cleaner return
            Rider[] toReturn = new Rider[exitCount];
            for (int i=0; i<exitCount; i++) {
                toReturn[i] = exiting[i];
            }
            for (int i=0; i<toReturn.length; i++) {
                Stats.addRiderData(toReturn[i]);
            }
            return toReturn;
        }
    }

    public boolean isFull() {
        if (numRiders == 50) { return true; }
        else { return false; }
    }

    public boolean isEmpty() {
        if (numRiders == 0) { return true; }
        else { return false; }
    }

    public boolean isExpress() {
        if (express) {return true;}
        else {return false;}
    }

    public static void main(String[] args) {
        Stop stop0 = new Stop();
        Rider rider0 = new Rider(0, 0);
        rider0.setDropStop(1);
        Rider rider1 = new Rider(0, 0);
        rider1.setDropStop(1);
        stop0.addToStop(rider0);
        stop0.addToStop(rider1);
        Bus bus0 = new Bus(false);
        stop0.boardFromStop(bus0);
        Rider[] offList = bus0.unloadBusAtStop(1);
        System.out.println(offList[0]);
    }
}
