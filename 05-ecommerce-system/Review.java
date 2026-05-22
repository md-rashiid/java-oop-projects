public class Review {
    private String reviewId;
    private String customerId;
    private String customerName;
    private int rating;
    private String comment;

    public Review(String reviewId, String customerId, String customerName, int rating, String comment) {
        if (rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be between 1 and 5");
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.rating = rating;
        this.comment = comment;
    }

    public void display() {
        String stars = "*".repeat(rating) + "-".repeat(5 - rating);
        System.out.println("  [" + stars + "] " + customerName + ": " + comment);
    }

    public int getRating()        { return rating; }
    public String getReviewId()   { return reviewId; }
    public String getCustomerId() { return customerId; }
    public String getComment()    { return comment; }
    public String getCustomerName() { return customerName; }
}
