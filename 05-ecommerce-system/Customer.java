import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private Cart cart;
    private List<Order> orderHistory;

    public Customer(String userId, String name, String email, String phone, String address) {
        super(userId, name, email, phone);
        this.address = address;
        this.cart = new Cart(userId);
        this.orderHistory = new ArrayList<>();
    }

    @Override
    public String getRole() { return "Customer"; }

    @Override
    public void displayDashboard() {
        System.out.println("=== CUSTOMER DASHBOARD ===");
        System.out.println("Name    : " + getName());
        System.out.println("Email   : " + getEmail());
        System.out.println("Phone   : " + getPhone());
        System.out.println("Address : " + address);
        System.out.println("Orders  : " + orderHistory.size());
        System.out.println("Cart items: " + cart.getTotalItems());
    }

    public Order placeOrder(PaymentMethod paymentMethod) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add items before placing order.");
            return null;
        }

        double total = cart.calculateTotal();
        boolean paid = paymentMethod.processPayment(total);

        if (!paid) {
            System.out.println("Payment failed. Order not placed.");
            return null;
        }

        String orderId = "ORD" + System.currentTimeMillis() % 100000;
        Order order = new Order(orderId, getUserId(), getName(), address,
                cart.getItems(), total,
                paymentMethod.getPaymentType(), paymentMethod.getTransactionId());

        orderHistory.add(order);
        cart.clear();
        return order;
    }

    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }
        System.out.println("=== ORDER HISTORY for " + getName() + " ===");
        for (Order o : orderHistory) o.displayOrder();
    }

    public Cart getCart()              { return cart; }
    public String getAddress()         { return address; }
    public List<Order> getOrderHistory() { return orderHistory; }
    public void setAddress(String address) { this.address = address; }

    void addOrderToHistory(Order order) { orderHistory.add(order); }
}
