class Book{
  private String bookId;
  private  String title;
  private String author;
  private String genre;
  private int totalCopies;
  private int availableCopies;

  Book(String bookId,String title,String author,String genre,int totalCopies,int availableCopies){
    this.bookId=bookId;
    this.title=title;
    this.author=author;
    this.genre=genre;
    this.totalCopies=totalCopies;
    this.availableCopies=availableCopies;

  }
  // Getter Method
  public String getBookId(){
    return bookId;
  }
  public String getTitle(){
    return title;
  }
  public String getAuthor(){
    return author;
  }
  public String getGenre(){
    return genre;
  }
  public int getTotalCopies(){
    return totalCopies;
  }
  public int getAvailableCopies(){
    return availableCopies;
  }
  public void setAvailableCopies(int availableCopies) {
    this.availableCopies = availableCopies;
  }

  public void setTotalCopies(int totalCopies) {
    this.totalCopies = totalCopies;
  }

  public void displayInfo(){
    System.out.println("------------------------Book Info---------------------");
    System.out.println("BOOK ID: " + bookId);
    System.out.println("TITLE: " +title);
    System.out.println("AUTHOR: " + author);
    System.out.println("GENRE: " + genre);
    System.out.println("TOTAL COPIES: " + totalCopies);
    System.out.println("AVAILABLE COPIES: " + availableCopies);
  }
  
}