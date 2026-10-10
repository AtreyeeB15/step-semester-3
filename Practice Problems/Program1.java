import java.util.ArrayList;
import java.util.List;

abstract class Vehicle1 {
    private String licensePlate;
    private String model;
    private boolean isRented;

    public Vehicle1(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.isRented = false;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }

    public abstract double calculateRentalCharge(int days);
}

class Sedan1 extends Vehicle1 {
    private static final double DAILY_RATE = 50.0;

    public Sedan1(String licensePlate, String model) {
        super(licensePlate, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class SUV1 extends Vehicle1 {
    private static final double DAILY_RATE = 80.0;

    public SUV1(String licensePlate, String model) {
        super(licensePlate, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class Truck1 extends Vehicle1 {
    private static final double DAILY_RATE = 100.0;

    public Truck1(String licensePlate, String model) {
        super(licensePlate, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class Customer1 {
    private String name;

    public Customer1(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Rental1 {
    private Customer1 customer;
    private Vehicle1 vehicle;
    private int days;

    public Rental1(Customer1 customer, Vehicle1 vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public static Rental1 rentVehicle(Customer1 customer, Vehicle1 vehicle, int days) {
        if (vehicle.isRented()) {
            System.out.printf("%s %s is currently unavailable.\n", vehicle.getModel(), vehicle.getLicensePlate());
            return null;
        }

        vehicle.setRented(true);
        double charge = vehicle.calculateRentalCharge(days);
        System.out.printf("%s %s rented successfully by %s. Rental charge: $%.2f.\n",
                vehicle.getModel(), vehicle.getLicensePlate(), customer.getName(), charge);
        return new Rental1(customer, vehicle, days);
    }

    public void returnVehicle() {
        vehicle.setRented(false);
        System.out.printf("%s %s returned by %s.\n",
                vehicle.getModel(), vehicle.getLicensePlate(), customer.getName());
    }
}

public class Program1 {
    public static void main(String[] args) {
        Vehicle1 sedanA = new Sedan1("A", "Sedan");
        Vehicle1 suvB = new SUV1("B", "SUV");

        Customer1 c1 = new Customer1("Customer 1");
        Customer1 c2 = new Customer1("Customer 2");
        Customer1 c3 = new Customer1("Customer 3");

        Rental1 r1 = Rental1.rentVehicle(c1, sedanA, 3);
        Rental1 r2 = Rental1.rentVehicle(c2, sedanA, 2);

        if (r1 != null) {
            r1.returnVehicle();
        }

        Rental1 r3 = Rental1.rentVehicle(c3, suvB, 5);
    }
}