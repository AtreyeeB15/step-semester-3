public class Problem4 {
    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k) {
            return 0;
        }

        long currentSum = 0;
        long targetSum = (long) k * threshold;
        int alertCount = 0;

        for (int i = 0; i < k; i++) {
            currentSum += readings[i];
        }

        if (currentSum >= targetSum) {
            alertCount++;
        }

        for (int i = k; i < readings.length; i++) {
            currentSum += readings[i] - readings[i - k];
            if (currentSum >= targetSum) {
                alertCount++;
            }
        }

        return alertCount;
    }
}