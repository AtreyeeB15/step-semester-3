import java.util.ArrayList;
import java.util.List;

abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCharge() { return charge; }
}

class QuickWash extends WashType {
    public QuickWash() { super("Quick", 30, 20.0); }
}

class NormalWash extends WashType {
    public NormalWash() { super("Normal", 45, 30.0); }
}

class HeavyWash extends WashType {
    public HeavyWash() { super("Heavy", 60, 45.0); }
}

class DelicateWash extends WashType {
    public DelicateWash() { super("Delicate", 40, 25.0); }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class WashingMachine {
    private String machineId;
    private boolean isBusy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.isBusy = false;
    }

    public String getMachineId() { return machineId; }
    public boolean isBusy() { return isBusy; }

    public void setBusy(boolean busy) { this.isBusy = busy; }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public boolean startWash() {
        if (machine.isBusy()) {
            System.out.printf("Machine %s is currently busy.\n", machine.getMachineId());
            return false;
        }
        machine.setBusy(true);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.\n",
                washType.getName(), machine.getMachineId(), student.getName(),
                washType.getDurationMinutes(), washType.getCharge());
        return true;
    }

    public void completeWash() {
        machine.setBusy(false);
        System.out.printf("%s cycle completed. %s is now free.\n",
                machine.getMachineId(), machine.getMachineId());
    }
}

public class Program1 {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle cycle1 = new WashCycle(asha, m1, new QuickWash());
        cycle1.startWash();

        WashCycle cycle2 = new WashCycle(ravi, m1, new HeavyWash());
        cycle2.startWash();

        WashCycle cycle3 = new WashCycle(ravi, m2, new HeavyWash());
        cycle3.startWash();

        cycle1.completeWash();

        WashCycle cycle4 = new WashCycle(neha, m1, new NormalWash());
        cycle4.startWash();
    }
}