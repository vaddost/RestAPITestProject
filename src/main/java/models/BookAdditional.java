package models;

import java.util.Objects;

public class BookAdditional {
    private int pageCount;
    private BookSize size;

    public BookAdditional() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookAdditional)) return false;
        BookAdditional that = (BookAdditional) o;
        return pageCount == that.pageCount && size.equals(that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount, size);
    }

    @Override
    public String toString() {
        return "{" +
                "pageCount=" + pageCount +
                ", size=" + size +
                '}';
    }

    public Builder getBuilder(){
        return new Builder();
    }

    public class Builder{
        private Builder(){

        }

        public Builder setPageCount(int pageCount){
            BookAdditional.this.pageCount = pageCount;
            return this;
        }

        public Builder setSize(double height, double width, double length){
            if (BookAdditional.this.size == null){
                BookAdditional.this.size = new BookSize();
            }
            BookAdditional.this.size =
                    BookAdditional.this.size.getBuilder()
                    .setHeight(height)
                    .setWidth(width)
                    .setLenght(length)
                    .build();
            return this;
        }

        public BookAdditional build(){
            return BookAdditional.this;
        }
    }
}
