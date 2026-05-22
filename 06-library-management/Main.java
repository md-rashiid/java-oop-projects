public class Main {
    public static void main(String[] args) {

        // ── 1. Library Create ────────────────────────────────────
        Library library = new Library("City Public Library");

        // ── 2. Books Add ─────────────────────────────────────────
        Book b1 = new Book("B001", "Head First Java", "Kathy Sierra", "Programming", 3, 3);
        Book b2 = new Book("B002", "Clean Code", "Robert Martin", "Programming", 2, 2);
        Book b3 = new Book("B003", "The Alchemist", "Paulo Coelho", "Fiction", 4, 4);
        Book b4 = new Book("B004", "Atomic Habits", "James Clear", "Self Help", 2, 2);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);

        // ── 3. Members Register ──────────────────────────────────
        Member m1 = new Member("M001", "Rashid",  "rashiid.cse@gmail.com",  "9263071036");
        Member m2 = new Member("M002", "Priya Sharma","priya@email.com", "9123456789");
        Member m3 = new Member("M003", "Rohit Verma", "rohit@email.com", "9988776655");

        library.addMember(m1);
        library.addMember(m2);
        library.addMember(m3);

        // ── 4. Display All Books ─────────────────────────────────
        System.out.println();
        library.displayAllBooks();

        // ── 5. Display All Members ───────────────────────────────
        System.out.println();
        library.displayAllMembers();

        // ── 6. Issue Books ───────────────────────────────────────
        System.out.println("\n--- Issuing Books ---");
        library.issueBook("M001", "B001"); // Aman → Head First Java
        library.issueBook("M002", "B001"); // Priya → Head First Java (2nd copy)
        library.issueBook("M003", "B002"); // Rohit → Clean Code

        // ── 7. Issue Validation Check ────────────────────────────
        System.out.println("\n--- Validation Checks ---");
        library.issueBook("M999", "B001"); // Invalid member
        library.issueBook("M001", "B999"); // Invalid book

        // ── 8. Search ────────────────────────────────────────────
        System.out.println("\n--- Search ---");
        library.searchByTitle("java");

        // ── 9. Return Book ───────────────────────────────────────
        System.out.println("\n--- Returning Books ---");
        library.returnBook("M001", "B001"); // Aman returns Head First Java
        library.returnBook("M003", "B002"); // Rohit returns Clean Code

        // ── 10. Return Validation ────────────────────────────────
        System.out.println("\n--- Return Validation ---");
        library.returnBook("M001", "B001"); // Already returned

        // ── 11. Final Inventory ──────────────────────────────────
        System.out.println("\n--- Final Book Status ---");
        library.displayAllBooks();
    }
}
