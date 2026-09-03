class Student {
    static {
        System.out.println("College info loaded");
    }

    public Student(String name) {
        System.out.println("Student record created: " + name);
    }
}

public class M4 {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};
        for (String name : names) {
            new Student(name);
        }
    }
}