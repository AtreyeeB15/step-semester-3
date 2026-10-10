import java.time.LocalDate;

enum LeaveStatus2 {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee2 {
    private String name;

    public Employee2(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public abstract boolean canRequestLeave(int days);
}

class FullTimeEmployee2 extends Employee2 {
    public FullTimeEmployee2(String name) { super(name); }

    @Override
    public boolean canRequestLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee2 extends Employee2 {
    public PartTimeEmployee2(String name) { super(name); }

    @Override
    public boolean canRequestLeave(int days) {
        return days <= 10;
    }
}

class LeaveRequest2 {
    private Employee2 employee;
    private String dateRange;
    private int days;
    private LeaveStatus2 status;

    public LeaveRequest2(Employee2 employee, String dateRange, int days) {
        this.employee = employee;
        this.dateRange = dateRange;
        this.days = days;
        this.status = LeaveStatus2.PENDING;
        System.out.printf("Leave request submitted for %s (%s). Status: Pending.\n",
                employee.getName(), dateRange);
    }

    public void approve(String reviewerName) {
        if (status != LeaveStatus2.PENDING) {
            System.out.printf("Cannot change leave request status from %s to Approved.\n", status);
            return;
        }
        status = LeaveStatus2.APPROVED;
        System.out.printf("%s's leave request (%s) approved. Status: Approved.\n",
                employee.getName(), dateRange);
    }

    public void reject(String reviewerName) {
        if (status != LeaveStatus2.PENDING) {
            System.out.printf("Cannot change leave request status from %s to Rejected.\n", status);
            return;
        }
        status = LeaveStatus2.REJECTED;
        System.out.printf("%s's leave request (%s) rejected. Status: Rejected.\n",
                employee.getName(), dateRange);
    }

    public void setPending() {
        if (status == LeaveStatus2.APPROVED || status == LeaveStatus2.REJECTED) {
            System.out.printf("Cannot change leave request status from %s to Pending.\n", status);
        } else {
            status = LeaveStatus2.PENDING;
        }
    }
}

public class Program2 {
    public static void main(String[] args) {
        Employee2 john = new FullTimeEmployee2("John");
        Employee2 jane = new PartTimeEmployee2("Jane");

        LeaveRequest2 req1 = new LeaveRequest2(john, "Jan 1-5", 5);
        req1.approve("Alice");

        LeaveRequest2 req2 = new LeaveRequest2(jane, "Feb 10-11", 2);
        req2.reject("Bob");

        req1.setPending();
    }
}