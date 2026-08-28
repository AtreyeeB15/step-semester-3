class AcademicCourse {
    String code;
    String title;
    int credits;
    int labCredits;

    public AcademicCourse(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    public AcademicCourse(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    public int totalCredits() {
        return credits + labCredits;
    }
}

public class CourseRegistryApp {
    public static void main(String[] args) {
        AcademicCourse course1 = new AcademicCourse("21CSC201J", "Data Structures", 4);
        AcademicCourse course2 = new AcademicCourse("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(course1.code + " total credits: " + course1.totalCredits());
        System.out.println(course2.code + " total credits: " + course2.totalCredits());
    }
}