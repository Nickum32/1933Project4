public class Stats {
    private static double maxWait = -1;  // tracks the longest passenger waiting time
    private static int maxLine = -1;  // tracks the longest bus stop line
    private static int busCap = 0;  // holds the total capacity used to calculate the average
    private static int busCapCount = 0;  // tracks how many values have been sent into busCap
    private static double riderTime = 0;  // holds the total rider time used to calculate the average
    private static int riderTimeCount = 0;  // tracks how many values have been sent into riderTime
    private static double riderWait = 0;  // holds total rider wait used to calculate the average
    private static int riderWaitCount = 0;  // tracks how many values have been sent into riderWait
    private static double riderBusTime = 0;  // holds total time riders spent on bus for average
    private static int riderBusTimeCount = 0;  // tracks how many values have been sent into riderBusTime
    private static int numStandardBuses = 0;  // tracks how many standard buses were used
    private static int numExpressBuses = 0;  // tracks how many express buses were used

    // addRiderData will take in the relevant info from each rider as they exit the simulation and add
    // this data to the overall pool for simulation info
    public static void addRiderData(Rider rider) {
        double wait = rider.getBoardTime()-rider.getStartTime();
        double totalTime = BusSim.agenda.getCurrentTime()-rider.getStartTime();
        double rideTime = BusSim.agenda.getCurrentTime()-rider.getBoardTime();
        if (wait > maxWait) {
            maxWait = wait;
        }
        riderWait += wait;
        riderWaitCount ++;
        riderTime += totalTime;
        riderTimeCount ++;
        riderBusTime += rideTime;
        riderBusTimeCount ++;
    }
    public static void addBusData(int capacity) {
        busCap += capacity;
        busCapCount ++;
    }
    public static void addLine(int line) {
        if (line > maxLine) {
            maxLine = line;
        }
    }
    public static void addBus(Bus bus) {
        if (bus.isExpress()) {
            numExpressBuses++;
        }
        else {
            numStandardBuses++;
        }
    }
    public static void getStats() {
        double avgWaitTime = riderWait / riderWaitCount;
        double avgTimeInSystem = riderTime / riderTimeCount;
        double avgTimeOnBus = riderBusTime / riderBusTimeCount;
        double avgBusCap = busCap / busCapCount;
        System.out.println("Average rider inter-arrival time: "+BusSim.riderTime+" seconds");
        System.out.println("Total sim time: "+BusSim.totalSimTime+" seconds");
        System.out.println("Number of standard buses: "+numStandardBuses);
        System.out.println("Number of express buses: "+numExpressBuses);
        System.out.println("Average rider wait time: "+avgWaitTime);
        System.out.println("Max wait time: "+maxWait);
        System.out.println("Average rider time spend in system: "+avgTimeInSystem);
        //System.out.println("Average time spend on the bus: "+avgTimeOnBus);
        System.out.println("Average bus capacity: "+avgBusCap);
    }
}
