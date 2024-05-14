import java.util.Timer;
import java.util.TimerTask;

public class AutomaticCharger {
    private boolean isCharging;
    private double currentAmpere;
    private double minAmpere;
    private double maxAmpere;
    private double voltage;
    private double chargingThreshold;
    private double pvSurplus;

    private long startDelay; // Time delay (in milliseconds) before starting charging
    private long stopDelay; // Time delay (in milliseconds) before stopping charging

    private long surplusAboveThresholdStartTime;
    private long surplusBelowThresholdStartTime;

    public AutomaticCharger(double minAmpere, double maxAmpere, double voltage, long startDelay, long stopDelay) {
        this.isCharging = false;
        this.currentAmpere = 0.0;
        this.minAmpere = minAmpere;
        this.maxAmpere = maxAmpere;
        this.voltage = voltage;
        this.chargingThreshold = minAmpere * voltage / 1000;
        this.pvSurplus = 0.0;

        this.startDelay = startDelay;
        this.stopDelay = stopDelay;

        this.surplusAboveThresholdStartTime = 0;
        this.surplusBelowThresholdStartTime = 0;
    }

    public void startCharging() {
        // Use a timer to periodically check PV surplus and adjust charging
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Simulate getting PV surplus from your system (replace this with actual data retrieval)
                pvSurplus = getPVSurplus();

                if (pvSurplus >= chargingThreshold) {
                    if (!isCharging && System.currentTimeMillis() - surplusAboveThresholdStartTime >= startDelay) {
                        // Start charging only if surplus has been consistently above threshold for specified delay
                        isCharging = true;
                        adjustChargingCurrent();
                        System.out.println("Charging started at " + currentAmpere + " A");
                    }
                    surplusAboveThresholdStartTime = System.currentTimeMillis();
                } else {
                    if (isCharging && System.currentTimeMillis() - surplusBelowThresholdStartTime >= stopDelay) {
                        // Stop charging only if surplus has been consistently below threshold for specified delay
                        isCharging = false;
                        currentAmpere = 0.0;
                        System.out.println("Charging stopped");
                    }
                    surplusBelowThresholdStartTime = System.currentTimeMillis();
                }

                if (isCharging) {
                    // Adjust charging current if already charging and surplus is within acceptable range
                    adjustChargingCurrent();
                    System.out.println("Charging adjusted to " + currentAmpere + " A");
                }
            }
        }, 0, 2000); // Check every 5 seconds (adjust as needed)
    }

    private void adjustChargingCurrent() {
        if (pvSurplus > 0) {
            double desiredAmpere = Math.min(maxAmpere, pvSurplus * 1000 / voltage);
            currentAmpere = Math.max(minAmpere, desiredAmpere);
        } else {
            currentAmpere = 0.0; // No surplus, stop charging
        }
    }

    private double getPVSurplus() {
        // Simulated method to get PV surplus (replace this with actual data retrieval)
        // Example: randomly generate surplus between 0 and 5 kW
        double simulatedPvSurPlus = Math.random() * 5;
        System.out.println("PV Sur Plus: " + this.pvSurplus);
        return simulatedPvSurPlus;
    }

    public static void main(String[] args) {
        double minAmpere = 6.0; // Minimum charging current (in A)
        double maxAmpere = 16.0; // Maximum charging current (in A)
        double voltage = 230.0; // Voltage required for charging (in volts)
        long startDelay = 6000; // 10 seconds delay before starting charging (adjust as needed, in milliseconds)
        long stopDelay = 10000; // 30 seconds delay before stopping charging (adjust as needed, in milliseconds)

        AutomaticCharger carCharger = new AutomaticCharger(minAmpere, maxAmpere, voltage, startDelay, stopDelay);
        carCharger.startCharging();
    }
}
