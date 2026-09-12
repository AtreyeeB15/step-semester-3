package mypackage;

public class LibraryMember {
    // Problem 1: Field reachability requirements
    private String membershipPin;
    String branchCode;         // package-private (default)
    double finesOwed;          // package-private (default)
    public String displayName;

    // Problem 4: JavaBean fields
    private String membershipId;
    private String name;
    private boolean premiumMember;

    @SuppressWarnings("unused")
    private String securityAnswer; // Write-only field

    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        // Write-once property
        if (this.membershipId == null && id != null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswer = "TRANSFORMED_" + answer.hashCode();
        }
    }

    public String getMembershipPin() {
        return membershipPin;
    }

    public void setMembershipPin(String membershipPin) {
        this.membershipPin = membershipPin;
    }
}