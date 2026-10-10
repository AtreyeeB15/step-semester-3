import java.util.ArrayList;
import java.util.List;

abstract class Question3 {
    private String prompt;
    private int points;

    public Question3(String prompt, int points) {
        this.prompt = prompt;
        this.points = points;
    }

    public String getPrompt() { return prompt; }
    public int getPoints() { return points; }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion3 extends Question3 {
    private String correctAnswer;

    public MultipleChoiceQuestion3(String prompt, int points, String correctAnswer) {
        super(prompt, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion3 extends Question3 {
    private String correctAnswer;

    public TrueFalseQuestion3(String prompt, int points, String correctAnswer) {
        super(prompt, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student3 {
    private String name;

    public Student3(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Examination3 {
    private String title;
    private List<Question3> questions = new ArrayList<>();

    public Examination3(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public void addQuestion(Question3 q) { questions.add(q); }
    public List<Question3> getQuestions() { return questions; }
}

class Attempt3 {
    private Student3 student;
    private Examination3 exam;
    private List<String> recordedAnswers = new ArrayList<>();
    private boolean isSubmitted = false;

    public Attempt3(Student3 student, Examination3 exam) {
        this.student = student;
        this.exam = exam;
        System.out.printf("%s started by %s.\n", exam.getTitle(), student.getName());
    }

    public void recordAnswer(int questionIndex, String answer) {
        if (isSubmitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        while (recordedAnswers.size() <= questionIndex) {
            recordedAnswers.add("");
        }
        recordedAnswers.set(questionIndex, answer);
        System.out.printf("Answer recorded for Question %d.\n", questionIndex + 1);
    }

    public void submit() {
        if (isSubmitted) {
            System.out.println("Exam already submitted.");
            return;
        }
        isSubmitted = true;
        System.out.printf("%s submitted by %s.\n", exam.getTitle(), student.getName());

        int totalScore = 0;
        int maxScore = 0;
        List<Question3> questions = exam.getQuestions();
        StringBuilder result = new StringBuilder("Result: ");

        for (int i = 0; i < questions.size(); i++) {
            Question3 q = questions.get(i);
            maxScore += q.getPoints();
            String ans = i < recordedAnswers.size() ? recordedAnswers.get(i) : "";
            boolean isCorrect = q.evaluate(ans);

            if (isCorrect) {
                totalScore += q.getPoints();
                result.append(String.format("Question %d: Correct (%d points)", i + 1, q.getPoints()));
            } else {
                result.append(String.format("Question %d: Incorrect (0 points)", i + 1));
            }

            if (i < questions.size() - 1) result.append(", ");
        }

        System.out.printf("%s. Total score: %d/%d.\n", result.toString(), totalScore, maxScore);
    }
}

public class Program3 {
    public static void main(String[] args) {
        Examination3 examA = new Examination3("Exam A");
        examA.addQuestion(new MultipleChoiceQuestion3("Q1", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion3("Q2", 5, "False"));

        Student3 s1 = new Student3("Student 1");
        Attempt3 attempt = new Attempt3(s1, examA);

        attempt.recordAnswer(0, "C");
        attempt.recordAnswer(1, "True");
        attempt.submit();

        attempt.recordAnswer(0, "A");
    }
}