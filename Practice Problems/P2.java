public class P2 {

    public static class LibraryMember {
        private String memberId;
        private int borrowLimit;
        private int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
        }

        public void borrowBook() {
            booksBorrowed++;
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public String displayInfo() {
            return "General Member | Books Borrowed: " + booksBorrowed;
        }
    }

    public static class StudentMember extends LibraryMember {
        private String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        public String getCourse() {
            return course;
        }

        @Override
        public String displayInfo() {
            return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    public static class HonorsStudentMember extends StudentMember {
        private int bonusLimit;

        public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
            super(memberId, borrowLimit, course);
            this.bonusLimit = bonusLimit;
        }

        @Override
        public String displayInfo() {
            return "Honors Student Member | Course: " + getCourse() + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    public static class FacultyMember extends LibraryMember {
        private String department;

        public FacultyMember(String memberId, int borrowLimit, String department) {
            super(memberId, borrowLimit);
            this.department = department;
        }

        @Override
        public String displayInfo() {
            return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Base root class";
        }
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            if (member != null) {
                total += member.getBooksBorrowed();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new LibraryMember("STU1", 3).displayInfo());
        System.out.println(new StudentMember("STU2", 3, "CSE").displayInfo());
        System.out.println(new HonorsStudentMember("STU3", 3, "ECE", 2).displayInfo());
        System.out.println(new FacultyMember("STU4", 5, "Physics").displayInfo());

        HonorsStudentMember honorsMember = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember facultyMember = new FacultyMember("STU4", 5, "Physics");
        StudentMember studentMember = new StudentMember("STU2", 3, "CSE");

        System.out.println(classifyGeneration(honorsMember));
        System.out.println(classifyGeneration(facultyMember));

        studentMember.borrowBook();
        studentMember.borrowBook();
        honorsMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();

        LibraryMember[] list = { studentMember, honorsMember, facultyMember };
        System.out.println(getTotalBooksBorrowed(list));
    }
}