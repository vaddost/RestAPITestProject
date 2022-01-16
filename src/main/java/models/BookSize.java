package models;

import java.util.Objects;

public class BookSize {
    private double height;
    private double width;
    private double length;

    public BookSize() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookSize)) return false;
        BookSize bookSize = (BookSize) o;
        return Double.compare(bookSize.height, height) == 0 && Double.compare(bookSize.width, width) == 0 && Double.compare(bookSize.length, length) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, length);
    }

    @Override
    public String toString() {
        return "{" +
                "height=" + height +
                ", weight=" + width +
                ", length=" + length +
                '}';
    }

    public Builder getBuilder(){
        return new Builder();
    }

    public class Builder{
        private Builder(){

        }

        public Builder setHeight(double height){
            BookSize.this.height = height;
            return this;
        }

        public Builder setWidth(double width){
            BookSize.this.width = width;
            return this;
        }

        public Builder setLenght(double length){
            BookSize.this.length = length;
            return this;
        }

        public BookSize build(){
            return BookSize.this;
        }
    }
}
