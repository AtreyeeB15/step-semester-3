public class P4 {

    public static class LibraryMember {
        private String memberId;
        private int borrowLimit;
        private int booksBorrowed = 0;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        public String displayInfo() {
            return "General | Books: " + booksBorrowed;
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
            return "Student | Course: " + course + " | Books: " + super.booksBorrowed;
        }
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (LibraryMember member : members) {
            sb.append(member.displayInfo());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                sb.append(" [Course via downcast: ").append(student.getCourse()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] array = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };
        System.out.println(batchPrint(array));
    }
}