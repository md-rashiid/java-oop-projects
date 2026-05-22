import java.time.LocalDate;

class BorrowRecord {
    private String recordId;
    private String memberId;
    private String bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    BorrowRecord(String recordId, String memberId, String bookId) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
        this.returnDate = null;
    }

    public String getRecordId() { return recordId; }
    public String getMemberId() { return memberId; }
    public String getBookId()   { return bookId; }
    public LocalDate getIssueDate()  { return issueDate; }
    public LocalDate getDueDate()    { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public double calculateFine() {
        if (!isReturned()) return 0;
        long daysLate = dueDate.until(returnDate).getDays();
        return daysLate > 0 ? daysLate * 5.0 : 0;
    }

    public void displayInfo() {
        System.out.println("------------ Borrow Record ------------");
        System.out.println("RECORD ID  : " + recordId);
        System.out.println("MEMBER ID  : " + memberId);
        System.out.println("BOOK ID    : " + bookId);
        System.out.println("ISSUE DATE : " + issueDate);
        System.out.println("DUE DATE   : " + dueDate);
        System.out.println("RETURNED   : " + isReturned());
        if (isReturned()) {
            System.out.println("RETURN DATE: " + returnDate);
            System.out.println("FINE       : Rs." + calculateFine());
        }
    }
}
