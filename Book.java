package DigitalLibraryManagement;
public class Book {
    private String title;
    private String author;
    private String ISBN;
    private int yearPublished;
    private String genre;
    private boolean isIssued;

    public Book(String title, String author, String ISBN, int yearPublished, String genre) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + 
               ", Year Published: " + yearPublished + ", Genre: " + genre + 
               ", Issued: " + (isIssued ? "Yes" : "No");
    }
}
