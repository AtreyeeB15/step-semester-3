import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class Room4 {
    private String roomNumber;

    public Room4(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }
    public abstract String getCategoryName();
    public abstract double calculatePrice(long days);
}

class StandardRoom4 extends Room4 {
    public StandardRoom4(String roomNumber) { super(roomNumber); }
    @Override public String getCategoryName() { return "Standard Room"; }
    @Override public double calculatePrice(long days) { return days * 100.0; }
}

class DeluxeRoom4 extends Room4 {
    public DeluxeRoom4(String roomNumber) { super(roomNumber); }
    @Override public String getCategoryName() { return "Deluxe Room"; }
    @Override public double calculatePrice(long days) { return days * 200.0; }
}

class Customer4 {
    private String name;

    public Customer4(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Reservation4 {
    private Customer4 customer;
    private Room4 room;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCancelled;

    public Reservation4(Customer4 customer, Room4 room, LocalDate startDate, LocalDate endDate) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCancelled = false;
    }

    public Room4 getRoom() { return room; }
    public boolean isCancelled() { return isCancelled; }

    public boolean overlaps(LocalDate start, LocalDate end) {
        if (isCancelled) return false;
        return !startDate.isAfter(end) && !start.isAfter(endDate);
    }

    public void cancel() {
        if (isCancelled) return;
        this.isCancelled = true;
        System.out.printf("Reservation for %s, %s %s (%s to %s) cancelled successfully.\n",
                customer.getName(), room.getCategoryName(), room.getRoomNumber(), startDate, endDate);
    }

    public static boolean checkAvailability(List<Reservation4> allReservations, Room4 room, LocalDate start, LocalDate end) {
        for (Reservation4 r : allReservations) {
            if (r.getRoom().getRoomNumber().equals(room.getRoomNumber()) && r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    public static Reservation4 createReservation(List<Reservation4> allReservations, Customer4 customer, Room4 room, LocalDate start, LocalDate end) {
        if (!checkAvailability(allReservations, room, start, end)) {
            System.out.printf("%s %s is not available from %s to %s.\n",
                    room.getCategoryName(), room.getRoomNumber(), start, end);
            return null;
        }

        long days = start.until(end).getDays();
        if (days <= 0) days = 1;
        double price = room.calculatePrice(days);

        Reservation4 res = new Reservation4(customer, room, start, end);
        allReservations.add(res);
        System.out.printf("Reservation confirmed for %s, %s %s (%s to %s). Price: $%.2f.\n",
                customer.getName(), room.getCategoryName(), room.getRoomNumber(), start, end, price);
        return res;
    }
}

public class Program4 {
    public static void main(String[] args) {
        List<Reservation4> systemReservations = new ArrayList<>();

        Room4 room101 = new StandardRoom4("101");
        Room4 room201 = new DeluxeRoom4("201");

        Customer4 customerA = new Customer4("Customer A");
        Customer4 customerB = new Customer4("Customer B");
        Customer4 customerC = new Customer4("Customer C");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);
        LocalDate jan3 = LocalDate.of(2026, 1, 3);
        LocalDate jan7 = LocalDate.of(2026, 1, 7);

        if (Reservation4.checkAvailability(systemReservations, room101, jan1, jan5)) {
            System.out.printf("%s %s is available from %s to %s.\n",
                    room101.getCategoryName(), room101.getRoomNumber(), jan1, jan5);
        }

        Reservation4 resA = Reservation4.createReservation(systemReservations, customerA, room101, jan1, jan5);
        Reservation4 resB = Reservation4.createReservation(systemReservations, customerB, room101, jan3, jan7);

        if (resA != null) {
            resA.cancel();
        }

        LocalDate feb10 = LocalDate.of(2026, 2, 10);
        LocalDate feb12 = LocalDate.of(2026, 2, 12);
        Reservation4 resC = Reservation4.createReservation(systemReservations, customerC, room201, feb10, feb12);
    }
}