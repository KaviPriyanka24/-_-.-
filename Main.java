package DigitalLibraryManagement;
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Admin admin = new Admin(library);
        User user = new User(library);

        // Admin operations
        System.out.println("=== Admin Operations ===\n");
        admin.addBook("Java Programming", "Author A", "123-456-789", 2021, "Programming");
        System.out.println();
        admin.addBook("Python Programming", "Author B", "987-654-321", 2019, "Programming");
        System.out.println();
        admin.addBook("Introduction to Algorithms", "Author C", "456-123-789", 2009, "Algorithms");
        System.out.println();

        System.out.println("Listing all books:\n");
        admin.viewAllBooks();
        System.out.println();

        System.out.println("Modifying a book:\n");
        admin.modifyBook("Python Programming", "Advanced Python", "Author B2", "987-654-321", 2023, "Advanced Programming");
        System.out.println();

        System.out.println("Listing all books after modification:\n");
        admin.viewAllBooks();
        System.out.println();

        // Separate output for better readability
        System.out.println("-------------------------\n");

        // User operations
        System.out.println("=== User Operations ===\n");
        System.out.println("Searching for a book by title:\n");
        user.searchBookByTitle("Java Programming");
        System.out.println();

        System.out.println("Searching for books by author:\n");
        user.searchBooksByAuthor("Author B");
        System.out.println();

        System.out.println("Searching for books by genre:\n");
        user.searchBooksByGenre("Programming");
        System.out.println();

        System.out.println("Issuing a book:\n");
        user.issueBook("Java Programming");
        System.out.println();

        System.out.println("Returning a book:\n");
        user.returnBook("Java Programming");
        System.out.println();

        System.out.println("Final list of all books:\n");
        user.viewBooks();
        System.out.println();
    }
}

