package mypackage;


import java.util.Arrays;

public class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        // Defensive copy on input to prevent external modification
        this.seatNumbers = (seatNumbers != null) ? seatNumbers.clone() : new String[0];
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        // Defensive copy on output so callers cannot mutate internal state
        return seatNumbers.clone();
    }

    // Wither pattern: returns a brand new object instead of modifying current state
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updatedSeats = this.seatNumbers.clone();
        if (index >= 0 && index < updatedSeats.length) {
            updatedSeats[index] = newSeat;
        }
        return new BookingReceipt(this.bookingId, updatedSeats);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }

        int processedCount = 0;
        int nullCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullCount++;
                continue;
            }

            processedCount++;
            if (receipt instanceof GroupBookingReceipt) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processedCount + " processed | " + nullCount + " null skipped | " 
                + groupCount + " group | " + individualCount + " individual";
    }
}