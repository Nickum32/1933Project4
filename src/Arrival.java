import java.util.Random;

public class Arrival implements IEvent{
    private int stop;
    public Arrival(int stop) {
        this.stop = stop;
    }
    private double[] arrivalPercents = {.75, .75, .5, .5, .5, .2, .2, .2, .2, 0, 0, -.2, -.2,
    -.2, -.2, -.5, -.5, -.5, -.75, -.75};

    public void run() {
        Rider newRider = new Rider(stop, (int) BusSim.agenda.getCurrentTime());
        // System.out.println(newRider);
        BusSim.stopArray[stop].addToStop(newRider);
        int rand = new Random().nextInt(20);
        int nextTime = (int) (BusSim.riderTime+(BusSim.riderTime*arrivalPercents[rand]));
        BusSim.agenda.add(new Arrival(stop), nextTime);

    }
}
