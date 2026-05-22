public class OrderItem {
    private String productId;
    private String productName;
    private double priceAtOrder;
    private int quantity;

    public OrderItem(String productId, String productName, double priceAtOrder, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.priceAtOrder = priceAtOrder;
        this.quantity = quantity;
    }

    public OrderItem(CartItem cartItem) {
        this.productId = cartItem.getProduct().getProductId();
        this.productName = cartItem.getProduct().getName();
        this.priceAtOrder = cartItem.getProduct().getPrice();
        this.quantity = cartItem.getQuantity();
    }

    public double getSubtotal() { return priceAtOrder * quantity; }

    public String getProductId()   { return productId; }
    public String getProductName() { return productName; }
    public double getPriceAtOrder(){ return priceAtOrder; }
    public int getQuantity()       { return quantity; }
}
