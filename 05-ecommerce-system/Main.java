import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       JAVA OOP E-COMMERCE SYSTEM         ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ── 1. ADMIN SETUP ───────────────────────────────────────────────
        System.out.println("\n--- [STEP 1] Admin Setup ---");
        Admin admin = new Admin("A001", "Ravi Kumar", "ravi@shop.com", "9999000001", "SUPER");
        admin.displayDashboard();

        // ── 2. INVENTORY: Add products ───────────────────────────────────
        System.out.println("\n--- [STEP 2] Loading Inventory ---");
        Inventory inventory = new Inventory();

        Electronics laptop  = new Electronics("E001", "Dell Laptop",   65000, "Intel i5, 8GB RAM, 512GB SSD", "Dell", 12);
        Electronics phone   = new Electronics("E002", "Samsung Galaxy", 25000, "6.5 inch, 128GB, 5G", "Samsung", 12);
        Electronics earbuds = new Electronics("E003", "Sony Earbuds",    3500, "Noise Cancelling, 24hr battery", "Sony", 6);

        Clothing tshirt  = new Clothing("C001", "Nike T-Shirt",   799, "Sports dry-fit t-shirt", "L", "Black", "Polyester");
        Clothing jeans   = new Clothing("C002", "Levi's Jeans",  2499, "Slim fit denim jeans",   "32", "Blue", "Denim");

        Book javaBook    = new Book("B001", "Head First Java",    599, "Best Java beginner book", "Kathy Sierra", "978-0-596-00712-6", "Programming");
        Book designBook  = new Book("B002", "Clean Code",         699, "Writing maintainable code", "Robert Martin", "978-0-13-235088-4", "Programming");

        admin.addProduct(inventory, laptop,     10);
        admin.addProduct(inventory, phone,      25);
        admin.addProduct(inventory, earbuds,    50);
        admin.addProduct(inventory, tshirt,    100);
        admin.addProduct(inventory, jeans,      40);
        admin.addProduct(inventory, javaBook,   30);
        admin.addProduct(inventory, designBook, 20);

        System.out.println("\n[Inventory]");
        inventory.displayInventory();

        // ── 3. CUSTOMERS ─────────────────────────────────────────────────
        System.out.println("\n--- [STEP 3] Customer Registration ---");
        Customer aman  = new Customer("U001", "Aman Singh",  "aman@email.com",  "9876543210", "Mumbai, MH");
        Customer priya = new Customer("U002", "Priya Sharma","priya@email.com", "9123456789", "Delhi, DL");

        aman.displayDashboard();

        // ── 4. BROWSE & SEARCH ───────────────────────────────────────────
        System.out.println("\n--- [STEP 4] Browse & Search ---");
        System.out.println("[Search: 'java']");
        for (Product p : inventory.searchByName("java")) p.displayDetails();

        System.out.println("\n[Filter: Electronics]");
        for (Product p : inventory.filterByCategory("Electronics")) p.displayDetails();

        // ── 5. ADD REVIEWS ───────────────────────────────────────────────
        System.out.println("\n--- [STEP 5] Product Reviews ---");
        laptop.addReview(new Review("R001", "U001", "Aman Singh",  5, "Amazing laptop! Super fast."));
        laptop.addReview(new Review("R002", "U002", "Priya Sharma", 4, "Good value for money."));
        javaBook.addReview(new Review("R003", "U001", "Aman Singh", 5, "Best book for Java beginners!"));

        System.out.println("[Dell Laptop Reviews]");
        laptop.displayReviews();
        System.out.println("Average Rating: " + String.format("%.1f", laptop.getAverageRating()) + "/5");

        // ── 6. SHOPPING - AMAN ───────────────────────────────────────────
        System.out.println("\n--- [STEP 6] Aman's Shopping ---");
        Cart amanCart = aman.getCart();
        amanCart.addItem(laptop,  1);
        amanCart.addItem(earbuds, 2);
        amanCart.addItem(javaBook, 1);

        System.out.println("\n[Aman's Cart]");
        amanCart.displayCart();

        // Update quantity
        System.out.println("\n[Updating earbuds quantity to 1]");
        amanCart.updateQuantity("E003", 1);
        amanCart.displayCart();

        // ── 7. PLACE ORDER - Credit Card ────────────────────────────────
        System.out.println("\n--- [STEP 7] Aman Places Order (Credit Card) ---");
        PaymentMethod creditCard = new CreditCardPayment("4111111111111234", "Aman Singh", "123");
        Order amanOrder = aman.placeOrder(creditCard);

        System.out.println("\n[Order Confirmation]");
        if (amanOrder != null) {
            amanOrder.displayOrder();
            // Update inventory stock
            inventory.reduceStock("E001", 1);
            inventory.reduceStock("E003", 1);
            inventory.reduceStock("B001", 1);
        }

        // ── 8. SHOPPING - PRIYA ──────────────────────────────────────────
        System.out.println("\n--- [STEP 8] Priya's Shopping ---");
        Cart priyaCart = priya.getCart();
        priyaCart.addItem(tshirt,   2);
        priyaCart.addItem(jeans,    1);
        priyaCart.addItem(designBook, 1);

        System.out.println("\n[Priya's Cart]");
        priyaCart.displayCart();

        // ── 9. PLACE ORDER - UPI ─────────────────────────────────────────
        System.out.println("\n--- [STEP 9] Priya Places Order (UPI) ---");
        PaymentMethod upi = new UPIPayment("priya@upi");
        Order priyaOrder = priya.placeOrder(upi);

        if (priyaOrder != null) {
            priyaOrder.displayOrder();
            inventory.reduceStock("C001", 2);
            inventory.reduceStock("C002", 1);
            inventory.reduceStock("B002", 1);
        }

        // ── 10. PLACE ORDER - COD ────────────────────────────────────────
        System.out.println("\n--- [STEP 10] Priya Places Another Order (COD) ---");
        priyaCart.addItem(phone, 1);
        PaymentMethod cod = new CashOnDelivery();
        Order priyaOrder2 = priya.placeOrder(cod);
        if (priyaOrder2 != null) {
            priyaOrder2.displayOrder();
            inventory.reduceStock("E002", 1);
        }

        // ── 11. ADMIN: Manage Orders ─────────────────────────────────────
        System.out.println("\n--- [STEP 11] Admin manages orders ---");
        List<Order> allOrders = new ArrayList<>();
        if (amanOrder  != null) allOrders.add(amanOrder);
        if (priyaOrder != null) allOrders.add(priyaOrder);
        if (priyaOrder2 != null) allOrders.add(priyaOrder2);

        admin.viewAllOrders(allOrders);

        if (amanOrder != null)  admin.updateOrderStatus(amanOrder,  "SHIPPED");
        if (priyaOrder != null) admin.updateOrderStatus(priyaOrder, "DELIVERED");

        // ── 12. CUSTOMER: Order History ──────────────────────────────────
        System.out.println("\n--- [STEP 12] Order History ---");
        aman.viewOrderHistory();
        priya.viewOrderHistory();

        // ── 13. ADMIN: Update Inventory ──────────────────────────────────
        System.out.println("\n--- [STEP 13] Admin restocks laptop ---");
        admin.updateStock(inventory, "E001", 15);

        System.out.println("\n[Updated Inventory]");
        inventory.displayInventory();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║        DEMO COMPLETE - ALL FEATURES       ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("OOP Concepts Used:");
        System.out.println("  Inheritance    -> Product, User hierarchies");
        System.out.println("  Polymorphism   -> PaymentMethod interface (CC/UPI/COD)");
        System.out.println("  Encapsulation  -> Private fields + public getters/setters");
        System.out.println("  Abstraction    -> Abstract Product, Abstract User");
        System.out.println("  Composition    -> Cart has CartItems, Order has OrderItems");
        System.out.println("  Strategy       -> PaymentMethod strategy pattern");
    }
}
