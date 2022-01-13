package service;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.Book;

import static io.restassured.RestAssured.*;

public class BooksService {
    private RequestSpecification requestSpecification;

    public BooksService(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response getAllBooksWithoutPagination(){
        return given()
                .spec(requestSpecification)
                .param("pagination", false)
                .when()
                .get("/books");
    }

    public Response getAllBooksOnPage(int pageNumber){
        return given()
                .spec(requestSpecification)
                .param("page", pageNumber)
                .when()
                .get("/books");
    }

    public Response getBookById(int bookId){
        return given()
                .spec(requestSpecification)
                .when()
                .get("/book/{bookId}", bookId);
    }

    public Response createNewBook(Book book, int authorId, int genreId){
        return given()
                .spec(requestSpecification)
                .body(book)
                .when()
                .post("/book/{authorId}/{genreId}", authorId, genreId);
    }

    public Response updateBook(Book book){
        return given()
                .spec(requestSpecification)
                .body(book)
                .when()
                .put("/book");
    }

    public Response deleteBook(int bookId){
        return given()
                .spec(requestSpecification)
                .when()
                .delete("/book/{bookId}", bookId);
    }
}
