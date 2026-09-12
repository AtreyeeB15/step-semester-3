package mypackage;

public class CirculationLedger {
    private static final String DEFAULT_BRANCH_CODE;

    static {
        DEFAULT_BRANCH_CODE = "PTL-MAIN-01";
    }

    public static String getDefaultBranchCode() {
        return DEFAULT_BRANCH_CODE;
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " + regular + " regular";
    }
}