package models;

import java.util.Objects;

public class Book {
    private int bookId;
    private String bookName;
    private String bookLanguage;
    private String bookDescription;
    private BookAdditional additional;
    private int publicationYear;

    public Book() {
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                publicationYear == book.publicationYear &&
                bookName.equals(book.bookName) &&
                bookDescription.equals(book.bookDescription) &&
                additional.equals(book.additional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, bookDescription, additional, publicationYear);
    }

    @Override
    public String toString() {
        return "{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", additional=" + additional +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
