import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface NotificationChannel {
    void send(Student student, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(Student student, String message) {
        System.out.printf("[Email → %s] %s\n", student.getName(), message);
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(Student student, String message) {
        System.out.printf("[SMS → %s] %s\n", student.getName(), message);
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(Student student, String message) {
        System.out.printf("[App → %s] %s\n", student.getName(), message);
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels;

    public Student(String name, String department, List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = channels;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getChannels() { return channels; }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<>();

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void postNotice(String title, List<String> targetDepartments) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Cannot post notice: Notice title is required.");
            return;
        }

        if (targetDepartments == null || targetDepartments.isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.printf("Notice '%s' posted to %s.\n", title, String.join(", ", targetDepartments));

        for (Student student : students) {
            if (targetDepartments.contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student, title);
                }
            }
        }
    }
}

public class Program5 {
    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        Student asha = new Student("Asha", "CSE", Arrays.asList(new EmailChannel(), new AppChannel()));
        Student ravi = new Student("Ravi", "ECE", Arrays.asList(new SmsChannel()));

        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        noticeBoard.postNotice("Lab Closed Tomorrow", Arrays.asList("CSE"));
        noticeBoard.postNotice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice("Sports Day", Arrays.asList());
    }
}