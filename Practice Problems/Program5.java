import java.util.ArrayList;
import java.util.List;

interface PaymentMethod5 {
    boolean processPayment(double amount);
}

class CreditCardPayment5 implements PaymentMethod5 {
    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}

class PayPalPayment5 implements PaymentMethod5 {
    private boolean shouldSucceed;

    public PayPalPayment5(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }
}

class Product5 {
    private String name;
    private double price;

    public Product5(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
}

class OrderItem5 {
    private Product5 product;
    private int quantity;

    public OrderItem5(Product5 product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() { return product.getPrice() * quantity; }
}

class Customer5 {
    private String name;

    public Customer5(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

enum OrderStatus5 {
    PENDING,
    PAID
}

class Order5 {
    private Customer5 customer;
    private List<OrderItem5> items = new ArrayList<>();
    private OrderStatus5 status;

    public Order5(Customer5 customer) {
        this.customer = customer;
        this.status = OrderStatus5.PENDING;
        System.out.printf("Order created for %s.\n", customer.getName());
    }

    public void addProduct(Product5 p, int qty) {
        items.add(new OrderItem5(p, qty));
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem5 item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public void processPayment(PaymentMethod5 paymentMethod, String methodName) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.printf("Payment initiated via %s for Order %s.\n", methodName, customer.getName());
        boolean success = paymentMethod.processPayment(calculateTotal());

        if (success) {
            this.status = OrderStatus5.PAID;
            System.out.printf("Payment for Order %s successful. Order status: Paid.\n", customer.getName());
        } else {
            System.out.printf("Payment for Order %s failed. Order status: Pending.\n", customer.getName());
        }
    }
}

public class Program5 {
    public static void main(String[] args) {
        Product5 prodA = new Product5("Product A", 100.0);
        Product5 prodB = new Product5("Product B", 50.0);
        Product5 prodC = new Product5("Product C", 75.0);

        Customer5 custX = new Customer5("Customer X");
        Order5 orderX = new Order5(custX);
        orderX.addProduct(prodA, 2);
        orderX.addProduct(prodB, 1);
        orderX.processPayment(new CreditCardPayment5(), "Credit Card");

        Customer5 custY = new Customer5("Customer Y");
        Order5 orderY = new Order5(custY);
        orderY.processPayment(new CreditCardPayment5(), "Credit Card");

        Customer5 custZ = new Customer5("Customer Z");
        Order5 orderZ = new Order5(custZ);
        orderZ.addProduct(prodC, 1);
        orderZ.processPayment(new PayPalPayment5(false), "PayPal");
    }
}