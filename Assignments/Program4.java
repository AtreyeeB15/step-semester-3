abstract class MembershipPlan {
    private String name;
    private int durationMonths;

    public MembershipPlan(String name, int durationMonths) {
        this.name = name;
        this.durationMonths = durationMonths;
    }

    public String getName() { return name; }
    public int getDurationMonths() { return durationMonths; }

    public abstract double calculateFee(double baseMonthlyRate);
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() { super("Monthly", 1); }

    @Override
    public double calculateFee(double baseMonthlyRate) {
        return baseMonthlyRate * getDurationMonths();
    }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() { super("Quarterly", 3); }

    @Override
    public double calculateFee(double baseMonthlyRate) {
        return baseMonthlyRate * getDurationMonths() * 0.90;
    }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() { super("Annual", 12); }

    @Override
    public double calculateFee(double baseMonthlyRate) {
        return baseMonthlyRate * getDurationMonths() * 0.75;
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;
    private static final double BASE_RATE = 1000.0;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
        
        double fee = plan.calculateFee(BASE_RATE);
        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: Active.\n",
                plan.getName(), member.getName(), fee);
    }

    public void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.printf("%s checked in successfully.\n", member.getName());
        } else {
            System.out.printf("Check-in denied: %s's membership is %s.\n", member.getName(), status);
        }
    }

    public void freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status == MembershipStatus.ACTIVE) {
            status = MembershipStatus.FROZEN;
            System.out.printf("%s's membership frozen. Status: Frozen.\n", member.getName());
        }
    }

    public void unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status == MembershipStatus.FROZEN) {
            status = MembershipStatus.ACTIVE;
            System.out.printf("%s's membership unfrozen. Status: Active.\n", member.getName());
        }
    }

    public void expire() {
        status = MembershipStatus.EXPIRED;
        System.out.printf("%s's membership expired. Status: Expired.\n", member.getName());
    }
}

public class Program4 {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();

        raviMembership.expire();
        raviMembership.freeze();
    }
}