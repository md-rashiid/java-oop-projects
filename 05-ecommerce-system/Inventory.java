import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Product> products;
    private Map<String, Integer> stock;

    public Inventory() {
        this.products = new HashMap<>();
        this.stock = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        products.put(product.getProductId(), product);
        stock.put(product.getProductId(), quantity);
    }

    public void removeProduct(String productId) {
        products.remove(productId);
        stock.remove(productId);
    }

    public boolean isInStock(String productId, int requiredQty) {
        return stock.getOrDefault(productId, 0) >= requiredQty;
    }

    public void updateStock(String productId, int quantity) {
        if (products.containsKey(productId)) {
            stock.put(productId, quantity);
        }
    }

    public void reduceStock(String productId, int quantity) {
        int current = stock.getOrDefault(productId, 0);
        stock.put(productId, Math.max(0, current - quantity));
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public int getStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Product> searchByName(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : products.values()) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> filterByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products.values()) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    public void displayInventory() {
        System.out.println("  ============================================");
        System.out.printf("  %-8s %-25s %-12s %8s %8s%n",
                "ID", "Name", "Category", "Price", "Stock");
        System.out.println("  ============================================");
        for (Product p : products.values()) {
            int qty = stock.getOrDefault(p.getProductId(), 0);
            String stockStatus = qty == 0 ? "OUT" : String.valueOf(qty);
            System.out.printf("  %-8s %-25s %-12s %8.0f %8s%n",
                    p.getProductId(), p.getName(), p.getCategory(), p.getPrice(), stockStatus);
        }
        System.out.println("  ============================================");
    }
}
