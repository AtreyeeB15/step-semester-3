class StudentMember {
    String name;
    int attendance;
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    public StudentMember(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class CollegeStudentApp {
    public static void main(String[] args) {
        new StudentMember("Ravi", 85);
        new StudentMember("Anitha", 90);

        StudentMember.printCollegeInfo();
    }
}