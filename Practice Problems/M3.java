class Account {
    private String regNo;
    private double totalFee;

    public Account(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    public final double calculateLateFee(int daysLate) {
        return totalFee * 0.1 * (daysLate / 10.0);
    }

    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            System.out.println(regNo + " | Total Fee: Rs " + totalFee + " | Late Fee: Rs " + calculateLateFee(daysLate));
        }
    }
}

public class M3 {
    public static void main(String[] args) {
        String[] regs = {"RA001", "RA002", "RA003", "RA004"};
        double[] fees = {200000, 150000, 180000, 220000};
        int[] days = {10, 0, -2, 5};

        for (int i = 0; i < regs.length; i++) {
            new Account(regs[i], fees[i]).printSummary(days[i]);
        }
    }
}