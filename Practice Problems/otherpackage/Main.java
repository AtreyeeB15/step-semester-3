package otherpackage;
import java.util.Arrays;
import mypackage.*;

public class Main {
    public static void main(String[] args) {
        // Example 1
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")); 
        // Output: ALLOWED

        // Example 2
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); 
        // Output: DENIED

        // Batch test across all 5 contexts for 'protected'
        String[][] batchAttempts = {
            {"protected", "SAME_CLASS"},                           // ALLOWED
            {"protected", "SAME_PACKAGE"},                         // ALLOWED
            {"protected", "DIFFERENT_PACKAGE"},                    // DENIED
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},   // ALLOWED
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"} // DENIED
        };

        System.out.println(AccessChecker.summarizeBatch(batchAttempts)); 
        // Output: Allowed: 3 | Denied: 2
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        CineScreen c = new CineScreen(2);
        c.bookSeat(); c.bookSeat(); c.bookSeat();
        System.out.println("Seats after 3 bookings: " + c.getSeatsAvailable()); // Output: 0

        c.cancelBooking(); c.cancelBooking(); c.cancelBooking();
        System.out.println("Seats after 3 cancels: " + c.getSeatsAvailable()); // Output: 2

        // --- PROBLEM 4: JavaBean Testing ---
        MovieBookingProfile profile = new MovieBookingProfile("Rahul Dev");
        System.out.println("Profile Name: " + profile.getName()); // Output: Rahul Dev

        profile.setConfirmed(true);
        System.out.println("Is Confirmed: " + profile.isConfirmed()); // Output: true

        profile.setOtp("4471"); // Write-only: setting OTP without a getter

        // --- PROBLEM 5: Immutable Receipt Testing ---
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X"; // Attempt to mutate returned array directly
        System.out.println("Original array after direct edit: " + b.getSeatNumbers()[0]); // Output: A1

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println("Original receipt: " + Arrays.toString(b.getSeatNumbers())); // ["A1", "A2"]
        System.out.println("Updated receipt: " + Arrays.toString(updated.getSeatNumbers())); // ["A1", "A3"]

        // Nightly Settlement Test
        BookingReceipt[] batch = new BookingReceipt[]{
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };

        System.out.println(BookingReceipt.processNightlySettlement(batch));
        // Output: "2 processed | 1 null skipped | 1 group | 1 individual"
    }
}