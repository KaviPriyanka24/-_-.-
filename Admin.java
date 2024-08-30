package DigitalLibraryManagement;

public class Admin {
    private Library library;

    public Admin(Library library) {
        this.library = library;
    }

    public void addBook(String title, String author, String ISBN, int yearPublished, String genre) {
        if (library.searchBookByTitle(title) != null || 
            library.searchBookByTitle(ISBN) != null) {
            System.out.println("Book already exists: " + title);
            return;
        }

        Book newBook = new Book(title, author, ISBN, yearPublished, genre);
        library.addBook(newBook);
        System.out.println("Book added successfully: " + newBook);
    }

    public void deleteBook(String title) {
        library.deleteBook(title);
        System.out.println("Book deleted successfully: " + title);
    }

    public void modifyBook(String oldTitle, String newTitle, String newAuthor, String newISBN, int newYearPublished, String newGenre) {
        Book book = library.searchBookByTitle(oldTitle);
        if (book != null) {
            library.deleteBook(oldTitle);
            addBook(newTitle, newAuthor, newISBN, newYearPublished, newGenre);
            System.out.println("Book modified successfully: " + newTitle);
        } else {
            System.out.println("Book not found: " + oldTitle);
        }
    }

    public void viewAllBooks() {
        library.listBooks();
    }
}
