import java.util.List;

public class Admin extends User {
    private String adminLevel;

    public Admin(String userId, String name, String email, String phone, String adminLevel) {
        super(userId, name, email, phone);
        this.adminLevel = adminLevel;
    }

    @Override
    public String getRole() { return "Admin"; }

    @Override
    public void displayDashboard() {
        System.out.println("=== ADMIN DASHBOARD ===");
        System.out.println("Name        : " + getName());
        System.out.println("Email       : " + getEmail());
        System.out.println("Admin Level : " + adminLevel);
    }

    public void addProduct(Inventory inventory, Product product, int quantity) {
        inventory.addProduct(product, quantity);
        System.out.println("[Admin] Product added: " + product.getName() + " | Stock: " + quantity);
    }

    public void removeProduct(Inventory inventory, String productId) {
        inventory.removeProduct(productId);
        System.out.println("[Admin] Product removed: " + productId);
    }

    public void updateStock(Inventory inventory, String productId, int quantity) {
        inventory.updateStock(productId, quantity);
        System.out.println("[Admin] Stock updated for " + productId + " -> " + quantity + " units");
    }

    public void viewAllOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders in system.");
            return;
        }
        System.out.println("=== ALL ORDERS ===");
        for (Order o : orders) o.displayOrder();
    }

    public void updateOrderStatus(Order order, String status) {
        order.updateStatus(status);
        System.out.println("[Admin] Order " + order.getOrderId() + " status -> " + status);
    }

    public String getAdminLevel() { return adminLevel; }
}
