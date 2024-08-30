package DigitalLibraryManagement;
import java.util.List;

public class User {
    private Library library;

    public User(Library library) {
        this.library = library;
    }

    public void viewBooks() {
        library.listBooks();
    }

    public void searchBookByTitle(String title) {
        Book book = library.searchBookByTitle(title);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    public void searchBooksByAuthor(String author) {
        List<Book> books = library.searchBooksByAuthor(author);
        if (!books.isEmpty()) {
            System.out.println("Books by " + author + ":");
            books.forEach(System.out::println);
        } else {
            System.out.println("No books found by " + author);
        }
    }

    public void searchBooksByGenre(String genre) {
        List<Book> books = library.searchBooksByGenre(genre);
        if (!books.isEmpty()) {
            System.out.println("Books in genre " + genre + ":");
            books.forEach(System.out::println);
        } else {
            System.out.println("No books found in genre " + genre);
        }
    }

    public void searchBooksByYear(int year) {
        List<Book> books = library.searchBooksByYear(year);
        if (!books.isEmpty()) {
            System.out.println("Books published in " + year + ":");
            books.forEach(System.out::println);
        } else {
            System.out.println("No books found published in " + year);
        }
    }

    public void issueBook(String title) {
        Book book = library.searchBookByTitle(title);
        if (book != null && !book.isIssued()) {
            book.setIssued(true);
            System.out.println("Book issued successfully: " + title);
        } else {
            System.out.println("Book is already issued or not found: " + title);
        }
    }

    public void returnBook(String title) {
        Book book = library.searchBookByTitle(title);
        if (book != null && book.isIssued()) {
            book.setIssued(false);
            System.out.println("Book returned successfully: " + title);
        } else {
            System.out.println("Book is not issued or not found: " + title);
        }
    }
}

