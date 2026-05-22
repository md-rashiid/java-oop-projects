public class Clothing extends Product {
    private String size;
    private String color;
    private String material;

    public Clothing(String productId, String name, double price, String description,
                    String size, String color, String material) {
        super(productId, name, price, description);
        this.size = size;
        this.color = color;
        this.material = material;
    }

    @Override
    public String getCategory() { return "Clothing"; }

    @Override
    public void displayDetails() {
        System.out.println("  [Clothing] " + getName() + " (ID: " + getProductId() + ")");
        System.out.println("  Size: " + size + " | Color: " + color + " | Material: " + material);
        System.out.println("  Price: Rs." + getPrice() + " | Rating: " + String.format("%.1f", getAverageRating()) + "/5");
        System.out.println("  " + getDescription());
    }

    public String getSize()     { return size; }
    public String getColor()    { return color; }
    public String getMaterial() { return material; }
}
