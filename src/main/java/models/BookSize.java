package models;

import java.util.Objects;

public class BookSize {
    private double height;
    private double weight;
    private double length;

    public BookSize() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookSize)) return false;
        BookSize bookSize = (BookSize) o;
        return Double.compare(bookSize.height, height) == 0 && Double.compare(bookSize.weight, weight) == 0 && Double.compare(bookSize.length, length) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, weight, length);
    }

    @Override
    public String toString() {
        return "{" +
                "height=" + height +
                ", weight=" + weight +
                ", length=" + length +
                '}';
    }
}
