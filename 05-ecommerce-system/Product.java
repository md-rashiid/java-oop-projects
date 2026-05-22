import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    private String productId;
    private String name;
    private double price;
    private String description;
    private List<Review> reviews;

    public Product(String productId, String name, double price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.reviews = new ArrayList<>();
    }

    public abstract String getCategory();
    public abstract void displayDetails();

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        double sum = 0;
        for (Review r : reviews) sum += r.getRating();
        return sum / reviews.size();
    }

    public void displayReviews() {
        if (reviews.isEmpty()) {
            System.out.println("  No reviews yet.");
            return;
        }
        for (Review r : reviews) r.display();
    }

    public String getProductId() { return productId; }
    public String getName()      { return name; }
    public double getPrice()     { return price; }
    public String getDescription() { return description; }
    public List<Review> getReviews() { return reviews; }

    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
}
