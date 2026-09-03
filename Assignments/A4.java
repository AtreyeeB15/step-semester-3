class MembershipCard {
    private static String libraryName;
    private static String validUntil;
    private String studentName;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
        // Using the static fields removes the "unused field" warning
        System.out.println("Membership card issued: " + this.studentName + " [" + libraryName + " - Valid till: " + validUntil + "]");
    }
}

public class A4 {
    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            new MembershipCard(name);
        }
    }
}