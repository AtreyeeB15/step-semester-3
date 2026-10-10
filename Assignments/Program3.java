import java.util.ArrayList;
import java.util.List;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() { return seatNumber; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 150.0; }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 250.0; }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 400.0; }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Show {
    private String movieName;
    private String time;
    private boolean isStarted;
    private List<String> bookedSeatNumbers = new ArrayList<>();

    public Show(String movieName, String time) {
        this.movieName = movieName;
        this.time = time;
        this.isStarted = false;
    }

    public boolean isStarted() { return isStarted; }
    public void setStarted(boolean started) { isStarted = started; }

    public boolean isSeatBooked(String seatNumber) {
        return bookedSeatNumbers.contains(seatNumber);
    }

    public void bookSeat(String seatNumber) {
        bookedSeatNumbers.add(seatNumber);
    }

    public void releaseSeat(String seatNumber) {
        bookedSeatNumbers.remove(seatNumber);
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean isCancelled;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.isCancelled = false;
    }

    public static Booking createBooking(Customer customer, Show show, List<Seat> seatsToBook) {
        if (seatsToBook.size() > 6) {
            System.out.println("Booking failed: Cannot book more than 6 seats per booking.");
            return null;
        }

        for (Seat seat : seatsToBook) {
            if (show.isSeatBooked(seat.getSeatNumber())) {
                System.out.printf("Seat %s is already booked for this show.\n", seat.getSeatNumber());
                return null;
            }
        }

        for (Seat seat : seatsToBook) {
            show.bookSeat(seat.getSeatNumber());
        }

        Booking booking = new Booking(customer, show, seatsToBook);
        
        List<String> seatNums = new ArrayList<>();
        double total = 0;
        for (Seat seat : seatsToBook) {
            seatNums.add(seat.getSeatNumber());
            total += seat.getPrice();
        }

        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.\n",
                customer.getName(), String.join(", ", seatNums), total);

        return booking;
    }

    public void cancelBooking() {
        if (show.isStarted()) {
            System.out.println("Cannot cancel booking after the show has started.");
            return;
        }

        if (isCancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        List<String> seatNums = new ArrayList<>();
        for (Seat seat : seats) {
            show.releaseSeat(seat.getSeatNumber());
            seatNums.add(seat.getSeatNumber());
        }

        isCancelled = true;
        System.out.printf("%s's booking cancelled. Seats %s released.\n",
                customer.getName(), String.join(", ", seatNums));
    }
}

public class Program3 {
    public static void main(String[] args) {
        Show show = new Show("Campus Movie", "7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        List<Seat> ashaSeats = List.of(
            new RegularSeat("A1"),
            new RegularSeat("A2"),
            new PremiumSeat("F5")
        );
        Booking ashaBooking = Booking.createBooking(asha, show, ashaSeats);

        List<Seat> raviSeats1 = List.of(new RegularSeat("A2"));
        Booking.createBooking(ravi, show, raviSeats1);

        List<Seat> raviSeats2 = List.of(new ReclinerSeat("R1"));
        Booking raviBooking = Booking.createBooking(ravi, show, raviSeats2);

        if (ashaBooking != null) {
            ashaBooking.cancelBooking();
        }

        List<Seat> nehaSeats = List.of(new RegularSeat("A2"));
        Booking.createBooking(neha, show, nehaSeats);
    }
}