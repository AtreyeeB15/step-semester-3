public class A5 {

    public static class GymMember {
        private static int enrollmentCounter = 2000;
        public final String membershipNumber;
        private int monthlyFee;
        private int feesPaid = 0;
        private String lastPaymentMode = "";

        public GymMember(int monthlyFee) {
            enrollmentCounter++;
            this.membershipNumber = "GYM-" + enrollmentCounter;
            this.monthlyFee = monthlyFee;
        }

        public static int getMembersEnrolled() {
            return enrollmentCounter - 2000;
        }

        public void payFee(int amount) {
            feesPaid += amount;
        }

        public void payFee(int amount, String mode) {
            this.lastPaymentMode = mode;
            payFee(amount);
        }

        public int getFeesPaid() {
            return feesPaid;
        }
    }

    public static class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'G') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(3));
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (members == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof GroupClassMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
                + groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        GymMember m1 = new GymMember(1000);
        System.out.println(m1.membershipNumber);
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(isValidReferralCode("G45B"));
        System.out.println(isValidReferralCode("G4B"));
        System.out.println(isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());

        GymMember[] batch = { new GroupClassMember(1500, "Zumba"), null, new GymMember(1000) };
        System.out.println(processWeeklyCheckIn(batch));
    }
}