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
}
