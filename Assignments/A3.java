import java.util.Arrays;

public class A3 {

    public static class GymMember {
        private String memberId;
        private int monthlyFee;
        private int[] lateFeeHistory = new int[10];
        private int lateFeeCount = 0;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {
            if (lateFeeCount < lateFeeHistory.length) {
                lateFeeHistory[lateFeeCount++] = amount;
            }
        }

        public int[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, lateFeeCount);
        }

        public int getTotalLateFees() {
            int total = 0;
            for (int i = 0; i < lateFeeCount; i++) {
                total += lateFeeHistory[i];
            }
            return total;
        }
    }

    public static class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(p.getLateFeeHistory()));
    }
}