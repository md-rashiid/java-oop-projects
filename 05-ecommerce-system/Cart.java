import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String customerId;
    private List<CartItem> items;

    public Cart(String customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(product.getProductId())) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("  Updated quantity: " + product.getName() + " x" + item.getQuantity());
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        System.out.println("  Added to cart: " + product.getName() + " x" + quantity);
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getProductId().equals(productId));
        System.out.println("  Item removed from cart.");
    }

    public void updateQuantity(String productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                if (quantity <= 0) {
                    removeItem(productId);
                } else {
                    item.setQuantity(quantity);
                    System.out.println("  Quantity updated to " + quantity);
                }
                return;
            }
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) total += item.getSubtotal();
        return total;
    }

    public int getTotalItems() {
        int count = 0;
        for (CartItem item : items) count += item.getQuantity();
        return count;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("  Cart is empty.");
            return;
        }
        System.out.println("  --------------------------------------------------");
        System.out.printf("  %-25s %6s %10s %12s%n", "Product", "Price", "Qty", "Subtotal");
        System.out.println("  --------------------------------------------------");
        for (CartItem item : items) {
            System.out.printf("  %-25s %6.0f %10d %12.0f%n",
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    item.getSubtotal());
        }
        System.out.println("  --------------------------------------------------");
        System.out.printf("  %-25s %29.0f%n", "TOTAL", calculateTotal());
        System.out.println("  --------------------------------------------------");
    }

    public void clear()              { items.clear(); }
    public boolean isEmpty()         { return items.isEmpty(); }
    public List<CartItem> getItems() { return items; }
    public String getCustomerId()    { return customerId; }
}
