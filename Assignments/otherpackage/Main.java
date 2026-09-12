package otherpackage;

import java.util.Arrays;
import mypackage.*;

public class Main {
    public static void main(String[] args) {
        // Problem 1: Modifier Group Summary
        String[][] attempts1 = {
            {"private","SAME_CLASS"},
            {"private","SAME_PACKAGE"},
            {"default","SAME_PACKAGE"},
            {"default","DIFFERENT_PACKAGE"},
            {"protected","SAME_PACKAGE"},
            {"protected","SAME_CLASS"},
            {"public","DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeByModifier(attempts1));

        // Problem 2: First Denied Attempt
        String[][] attempts2 = {
            {"public","SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected","SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts2));

        String[][] attempts3 = {
            {"public","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts3));

        // Problem 3: Book Inventory Guard
        BookInventory b = new BookInventory(3);
        b.checkOut(); b.checkOut(); b.checkOut(); b.checkOut();
        System.out.println("Available copies: " + b.getCopiesAvailable()); // 0

        b.checkIn(); b.checkIn(); b.checkIn(); b.checkIn();
        System.out.println("Available copies: " + b.getCopiesAvailable()); // 3

        // Problem 4: JavaBean Write-Once / Write-Only
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println("Membership ID: " + m.getMembershipId());

        m.setMembershipId("FAKE-0000"); // Ignored
        System.out.println("Membership ID after 2nd set: " + m.getMembershipId()); // LIB-8841
        System.out.println("Is Premium: " + m.isPremiumMember());
        m.setSecurityAnswer("BlueMountain");

        // Problem 5: Immutable Loan Receipt & Nightly Circulation
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100","BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("Defensive copy check: " + r.getBookIds()[0]); // BK-100

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Original: " + Arrays.toString(r.getBookIds()));
        System.out.println("Corrected: " + Arrays.toString(corrected.getBookIds()));

        LoanReceipt[] batch = new LoanReceipt[]{
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(CirculationLedger.processNightlyCirculation(batch));
    }
}