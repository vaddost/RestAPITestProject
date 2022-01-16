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

    public int getBookId(){
        return bookId;
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

    public Builder getBuilder(){
        return new Builder();
    }

    public class Builder{
        private Builder(){

        }

        public Builder setBookId(int bookId){
            Book.this.bookId = bookId;
            return this;
        }

        public Builder setBookName(String bookName){
            Book.this.bookName = bookName;
            return this;
        }

        public Builder setBookDescription(String bookDescription){
            Book.this.bookDescription = bookDescription;
            return this;
        }

        public Builder setBookLanguage(String bookLanguage){
            Book.this.bookLanguage = bookLanguage;
            return this;
        }

        public Builder setPublicationYear(int publicationYear){
            Book.this.publicationYear = publicationYear;
            return this;
        }

        public Builder setPageCount(int pageCount){
            if (Book.this.additional == null){
                Book.this.additional = new BookAdditional();
            }
            Book.this.additional = Book.this.additional.getBuilder()
                    .setPageCount(pageCount)
                    .build();
            return this;
        }

        public Builder setSize(double height, double width, double length){
            if (Book.this.additional == null){
                Book.this.additional = new BookAdditional();
            }
            Book.this.additional = Book.this.additional.getBuilder()
                    .setSize(height, width, length)
                    .build();
            return this;
        }

        public Book build(){
            return Book.this;
        }
    }
}
