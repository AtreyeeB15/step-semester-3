package mypackage;

public class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    @SuppressWarnings ("unused")
    private String otp; // Write-only property

    // Public no-argument constructor required by JavaBeans standard
    public MovieBookingProfile() {
    }

    // Convenience constructor chaining to no-arg constructor via this()
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // JavaBean convention uses isX() for boolean getters
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // Write-only property: setter provided, no getter exists anywhere
    public void setOtp(String otp) {
        this.otp = otp;
    }
}