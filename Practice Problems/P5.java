public class P5 {

    public static class LibraryMember {
        private static int enrollmentCounter = 100;
        private final String memberNumber;
        private int borrowLimit;
        private int booksBorrowed = 0;
        private String lastGenreBorrowed = "";

        public LibraryMember(int borrowLimit) {
            enrollmentCounter++;
            this.memberNumber = "LIB-" + enrollmentCounter;
            this.borrowLimit = borrowLimit;
        }

        public String getMemberNumber() {
            return memberNumber;
        }

        public static int getMembersEnrolled() {
            return enrollmentCounter - 100;
        }

        public void borrowBook() {
            booksBorrowed++;
        }

        public void borrowBook(String genre) {
            this.lastGenreBorrowed = genre;
            borrowBook();
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }
    }

    public static class FacultyMember extends LibraryMember {
        private String department;

        public FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
            this.department = department;
        }

        public String getDepartment() {
            return department;
        }
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(3));
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        if (members == null) {
            return "0 processed | 0 null skipped | 0 faculty | 0 regular";
        }

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof FacultyMember) {
                facultyCount++;
            } else {
                regularCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
                + facultyCount + " faculty | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.getMemberNumber());
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(isValidRenewalCode("R12A"));
        System.out.println(isValidRenewalCode("R1A"));
        System.out.println(isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] batch = { new FacultyMember(5, "Physics"), null, new LibraryMember(3) };
        System.out.println(processNightlyAudit(batch));
    }
}