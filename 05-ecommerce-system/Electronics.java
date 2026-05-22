public class Electronics extends Product {
    private String brand;
    private int warrantyMonths;

    public Electronics(String productId, String name, double price, String description,
                       String brand, int warrantyMonths) {
        super(productId, name, price, description);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getCategory() { return "Electronics"; }

    @Override
    public void displayDetails() {
        System.out.println("  [Electronics] " + getName() + " (ID: " + getProductId() + ")");
        System.out.println("  Brand: " + brand + " | Warranty: " + warrantyMonths + " months");
        System.out.println("  Price: Rs." + getPrice() + " | Rating: " + String.format("%.1f", getAverageRating()) + "/5");
        System.out.println("  " + getDescription());
    }

    public String getBrand()       { return brand; }
    public int getWarrantyMonths() { return warrantyMonths; }
}
