public class A2 {

    public static class GymMember {
        private String memberId;
        private int monthlyFee;
        private int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public String displayInfo() {
            return "Standard Member | Sessions: " + sessionsAttended;
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
            return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
        }
    }

    public static class EliteMember extends PremiumMember {
        private String lockerNumber;

        public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
            super(memberId, monthlyFee, trainerName);
            this.lockerNumber = lockerNumber;
        }

        @Override
        public String displayInfo() {
            return "Elite Member | Trainer: " + getTrainerName() + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
        }
    }

    public static class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(String memberId, int monthlyFee, String className) {
            super(memberId, monthlyFee);
            this.className = className;
        }

        @Override
        public String displayInfo() {
            return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
        }
    }

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Base root class";
        }
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            if (member != null) {
                total += member.getSessionsAttended();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new GymMember("MEM1", 1000).displayInfo());
        System.out.println(new PremiumMember("MEM2", 2000, "Coach Riya").displayInfo());
        System.out.println(new EliteMember("MEM3", 3000, "Coach Arjun", "L12").displayInfo());
        System.out.println(new GroupClassMember("MEM4", 1500, "Zumba").displayInfo());

        EliteMember eliteMember = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember groupClassMember = new GroupClassMember("MEM4", 1500, "Zumba");
        PremiumMember premiumMember = new PremiumMember("MEM2", 2000, "Coach Riya");

        System.out.println(classifyGeneration(eliteMember));
        System.out.println(classifyGeneration(groupClassMember));

        premiumMember.attendSession();
        premiumMember.attendSession();
        premiumMember.attendSession();

        eliteMember.attendSession();
        eliteMember.attendSession();

        groupClassMember.attendSession();
        groupClassMember.attendSession();
        groupClassMember.attendSession();
        groupClassMember.attendSession();

        GymMember[] mix = { premiumMember, eliteMember, groupClassMember };
        System.out.println(getTotalSessionsAttended(mix));
    }
}