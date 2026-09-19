public class A4 {

    public static class GymMember {
        private String memberId;
        private int monthlyFee;
        private int sessionsAttended = 0;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
        }
    }

    public static class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium | Trainer: " + trainerName + " | Sessions: " + super.sessionsAttended;
        }
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (GymMember member : members) {
            sb.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                sb.append(" [Trainer via downcast: ").append(premium.getTrainerName()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        GymMember[] array = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        System.out.println(batchPrint(array));
    }
}