import java.util.ArrayList;
class Member{
    private  String memberId;
    private String name;
    private String email;
    private String phoneNo;
    private ArrayList<Book> borrowedBooks;

    Member(String memberId,String name,String email,String phoneNo){
        this.memberId=memberId;
        this.name=name;
        this.email=email;
        this.phoneNo=phoneNo;
        this.borrowedBooks = new ArrayList<>();
    }
    public String getMemberId(){
        return memberId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNo(){
        return phoneNo;
    }    
    public ArrayList<Book> getBorrowedBooks(){
        return borrowedBooks;
    }
    public void addBorrowedBook(Book book){
        borrowedBooks.add(book);

    }
    public void removeBorrowedBook(Book book){
        borrowedBooks.remove(book);
    }
    public void displayInfo(){
        System.out.println("-----------------------Member Info-----------------");
        System.out.println("MEMBER ID: "  + memberId);
        System.out.println("NAME: " + name);
        System.out.println("EMAIL: " + email);
        System.out.println("PHONE NO: " + phoneNo);
         System.out.println("BORROWED BOOKS: " + borrowedBooks.size());
        
    }
}