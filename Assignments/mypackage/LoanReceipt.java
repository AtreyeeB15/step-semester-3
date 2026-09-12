package mypackage;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = (bookIds != null) ? bookIds.clone() : new String[0];
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updatedIds = this.bookIds.clone();
        if (index >= 0 && index < updatedIds.length) {
            updatedIds[index] = newId;
        }
        return new LoanReceipt(this.memberId, updatedIds);
    }
}