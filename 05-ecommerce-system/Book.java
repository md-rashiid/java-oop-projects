public class Book extends Product {
    private String author;
    private String isbn;
    private String genre;

    public Book(String productId, String name, double price, String description,
                String author, String isbn, String genre) {
        super(productId, name, price, description);
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    @Override
    public String getCategory() { return "Books"; }

    @Override
    public void displayDetails() {
        System.out.println("  [Book] " + getName() + " (ID: " + getProductId() + ")");
        System.out.println("  Author: " + author + " | ISBN: " + isbn + " | Genre: " + genre);
        System.out.println("  Price: Rs." + getPrice() + " | Rating: " + String.format("%.1f", getAverageRating()) + "/5");
        System.out.println("  " + getDescription());
    }

    public String getAuthor() { return author; }
    public String getIsbn()   { return isbn; }
    public String getGenre()  { return genre; }
}
