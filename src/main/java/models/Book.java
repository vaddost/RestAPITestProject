package models;

import java.util.Objects;

public class Book {
    private int bookId;
    private String bookName;
    private String bookDescription;
    private BookAdditional additional;
    private int publicationYear;

    public Book() {
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
}
