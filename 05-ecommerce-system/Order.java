import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private String customerName;
    private String deliveryAddress;
    private List<OrderItem> items;
    private double totalAmount;
    private String status;
    private String paymentType;
    private String transactionId;
    private long orderTimestamp;

    public Order(String orderId, String customerId, String customerName, String deliveryAddress,
                 List<CartItem> cartItems, double totalAmount,
                 String paymentType, String transactionId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
        this.transactionId = transactionId;
        this.status = "CONFIRMED";
        this.orderTimestamp = System.currentTimeMillis();

        this.items = new ArrayList<>();
        for (CartItem ci : cartItems) {
            this.items.add(new OrderItem(ci));
        }
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void displayOrder() {
        System.out.println("  ================================================");
        System.out.println("  Order ID    : " + orderId);
        System.out.println("  Customer    : " + customerName + " (ID: " + customerId + ")");
        System.out.println("  Address     : " + deliveryAddress);
        System.out.println("  Status      : " + status);
        System.out.println("  Payment     : " + paymentType + " | Txn: " + transactionId);
        System.out.println("  ------------------------------------------------");
        System.out.printf("  %-25s %6s %10s %12s%n", "Product", "Price", "Qty", "Subtotal");
        System.out.println("  ------------------------------------------------");
        for (OrderItem item : items) {
            System.out.printf("  %-25s %6.0f %10d %12.0f%n",
                    item.getProductName(), item.getPriceAtOrder(),
                    item.getQuantity(), item.getSubtotal());
        }
        System.out.println("  ------------------------------------------------");
        System.out.printf("  %-25s %29.0f%n", "TOTAL (Rs.)", totalAmount);
        System.out.println("  ================================================");
    }

    public String getOrderId()        { return orderId; }
    public String getCustomerId()     { return customerId; }
    public String getCustomerName()   { return customerName; }
    public String getStatus()         { return status; }
    public double getTotalAmount()    { return totalAmount; }
    public String getPaymentType()    { return paymentType; }
    public String getTransactionId()  { return transactionId; }
    public List<OrderItem> getItems() { return items; }
}
