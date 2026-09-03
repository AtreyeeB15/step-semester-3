class Staff {
    private String id;
    private double salary;

    public Staff(String id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary += salary;
    }

    public void print() {
        System.out.println(id + " | Final Salary: Rs " + salary);
    }
}

public class M2 {
    public static void main(String[] args) {
        double[] salaries = {40000, 55000, 62000, 48000};

        for (int i = 0; i < salaries.length; i++) {
            Staff s = new Staff("E-10" + (i + 1), salaries[i]);
            s.raiseSalary(5000);
            s.print();
        }
    }
}