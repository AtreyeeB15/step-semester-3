import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment2 {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public Assignment2(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }

    public abstract double calculateFinalMarks(double rawMarks, long daysLate);
}

class CodingAssignment2 extends Assignment2 {
    public CodingAssignment2(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, long daysLate) {
        if (daysLate <= 0) return rawMarks;
        double penaltyRate = 0.10 * daysLate;
        return Math.max(0, rawMarks * (1 - penaltyRate));
    }
}

class WrittenAssignment2 extends Assignment2 {
    public WrittenAssignment2(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, long daysLate) {
        if (daysLate <= 0) return rawMarks;
        double penaltyRate = 0.20 * daysLate;
        return Math.max(0, rawMarks * (1 - penaltyRate));
    }
}

class Student2 {
    private String name;

    public Student2(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

enum Status2 {
    SUBMITTED,
    GRADED
}

class Submission2 {
    private Student2 student;
    private Assignment2 assignment;
    private LocalDate submissionDate;
    private Status2 status;
    private double finalMarks;

    public Submission2(Student2 student, Assignment2 assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = Status2.SUBMITTED;
        
        long daysLate = ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
        if (daysLate <= 0) {
            System.out.printf("%s's submission for '%s' received (on time). Status: Submitted.\n",
                    student.getName(), assignment.getTitle());
        } else {
            System.out.printf("%s's submission for '%s' received (%d days late). Status: Submitted.\n",
                    student.getName(), assignment.getTitle(), daysLate);
        }
    }

    public void grade(double rawMarks) {
        if (status == Status2.GRADED) {
            System.out.printf("Submission for '%s' is already graded.\n", assignment.getTitle());
            return;
        }

        long daysLate = ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
        this.finalMarks = assignment.calculateFinalMarks(rawMarks, daysLate);
        this.status = Status2.GRADED;

        if (daysLate <= 0) {
            System.out.printf("%s graded: %.0f/%d. Status: Graded.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks());
        } else {
            int penaltyPercentage = (int) (daysLate * (assignment instanceof CodingAssignment2 ? 10 : 20));
            System.out.printf("%s graded: %.0f/%d after %d%% late penalty. Status: Graded.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks(), penaltyPercentage);
        }
    }

    public void resubmit(LocalDate newDate) {
        if (status == Status2.GRADED) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.\n", assignment.getTitle());
            return;
        }
        this.submissionDate = newDate;
        System.out.printf("%s resubmitted '%s'.\n", student.getName(), assignment.getTitle());
    }
}

public class Program2 {
    public static void main(String[] args) {
        Assignment2 linkedListLab = new CodingAssignment2("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment2 designEssay = new WrittenAssignment2("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Student2 asha = new Student2("Asha");
        Student2 ravi = new Student2("Ravi");

        Submission2 sub1 = new Submission2(asha, linkedListLab, LocalDate.of(2026, 3, 10));
        Submission2 sub2 = new Submission2(ravi, designEssay, LocalDate.of(2026, 3, 14));

        sub1.grade(45);
        sub2.grade(40);

        sub1.resubmit(LocalDate.of(2026, 3, 11));
    }
}