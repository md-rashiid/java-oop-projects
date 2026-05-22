import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

class Library {
    private String libraryName;
    private HashMap<String, Book> books;
    private HashMap<String, Member> members;
    private ArrayList<BorrowRecord> borrowRecords;
    private int recordCounter;

    Library(String libraryName) {
        this.libraryName   = libraryName;
        this.books         = new HashMap<>();
        this.members       = new HashMap<>();
        this.borrowRecords = new ArrayList<>();
        this.recordCounter = 1;
    }

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        System.out.println("Book added: " + book.getTitle());
    }
    public void addMember(Member member){
        members.put(member.getMemberId(),member);
        System.out.println("Member registered: " + member.getName());
    }
    public void issueBook(String memberId, String bookId) {
        Member member = members.get(memberId);
        Book book = books.get(bookId);

        if (member == null) {
            System.out.println("Member not found: " + memberId);
            return;
        }
        if (book == null) {
            System.out.println("Book not found: " + bookId);
            return;
        }
        if (book.getAvailableCopies() == 0) {
            System.out.println("No copies available: " + book.getTitle());
            return;
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        member.addBorrowedBook(book);

        String recordId = "REC" + recordCounter++;
        borrowRecords.add(new BorrowRecord(recordId, memberId, bookId));

        System.out.println("Issued: " + book.getTitle() + " -> " + member.getName());
    }

    public void returnBook(String memberId, String bookId) {
        Member member = members.get(memberId);
        Book book = books.get(bookId);

        if (member == null || book == null) {
            System.out.println("Invalid member or book ID.");
            return;
        }

        BorrowRecord record = null;
        for (BorrowRecord r : borrowRecords) {
            if (r.getMemberId().equals(memberId) && r.getBookId().equals(bookId) && !r.isReturned()) {
                record = r;
                break;
            }
        }

        if (record == null) {
            System.out.println("No active borrow record found.");
            return;
        }

        record.setReturnDate(LocalDate.now());
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        member.removeBorrowedBook(book);

        double fine = record.calculateFine();
        System.out.println("Returned: " + book.getTitle() + " by " + member.getName());
        if (fine > 0) System.out.println("Fine: Rs." + fine);
    }

    public void searchByTitle(String keyword) {
        System.out.println("Search results for: " + keyword);
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                book.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No books found.");
    }

    public void displayAllBooks() {
        System.out.println("========== All Books ==========");
        for (Book b : books.values()) b.displayInfo();
    }

    public void displayAllMembers() {
        System.out.println("========== All Members ==========");
        for (Member m : members.values()) m.displayInfo();
    }


  
}