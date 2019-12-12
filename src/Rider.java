// Rider class holds data for each distinct rider
import java.util.Random;

public class Rider {
    // stop rider boards the bus
    private int pickupStop;
    // stop rider gets off the bus
    private int dropStop;
    // time rider gets to pickup stop
    private double startTime;
    // express tracks whether the rider can board an express bus to reach their destination
    private boolean express;
    // board time track the time the rider boarded a bus
    private double boardTime;

    public Rider(int pickupStop, int startTime) {
        this.pickupStop = pickupStop;
        this.dropSelect();
        this.express = this.express();
        this.startTime = startTime;
    }
    // stopSelect handles the random selection of a passenger's dropoff stop
    private void dropSelect() {
        dropStop = -1;
        if (pickupStop < 15) {
            // dropArray holds the choices of stops for an east bound rider, giving double preference to downtown stops
            int[] dropArray = {1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15,15};
            while (pickupStop >= dropStop) {
                int random = new Random().nextInt(18);
                dropStop = dropArray[random];
            }
        }
        else {
            // condition for west bound riders
            int[] dropArray = {16,16,17,18,19,20,21,22,23,24,25,26,27,28,29,29,0,0};
            while (pickupStop >= dropStop && dropStop != 0) {
                int rand = new Random().nextInt(18);
                dropStop = dropArray[rand];
            }
        }
    }
    public int getDropStop() {
        return dropStop;
    }

    // check if this rider can board an express bus or not based on destination
    private boolean express() {
        int[] expressStops = {0,1,14,15,16,29,4,8,12,20,24,28};
        for (int i=0; i<expressStops.length; i++) {
            if (dropStop == expressStops[i]) {
                return true;
            }
        }
        return false;
    }
    public boolean getExpress() {
        return express;
    }
    public String toString() {
        String toReturn = "Pickup: "+pickupStop+", Dropoff: "+dropStop+", Start Time: "+startTime
                +", Express: ";
        if (express)
            toReturn += "True";
        else
            toReturn += "False";
        return toReturn;
    }
    // this method is for debugging purposes only
    public void setDropStop(int stop) {
        dropStop = stop;
    }
    public void setBoardTime(double time) {
        boardTime = time;
    }
    public double getStartTime() {
        return startTime;
    }
    public double getBoardTime() {
        return boardTime;
    }
    public static void main(String[] args) {
        Rider rider0 = new Rider(15, 0);
        System.out.println(rider0);
    }
}
