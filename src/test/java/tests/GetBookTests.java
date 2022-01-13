package tests;

import io.restassured.response.Response;
import models.Book;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class GetBookTests extends BaseTests{

    @Test
    public void checkGetBookWithExistedId(){
        int bookId = 51;
        Book book = booksService
                        .getBookById(bookId)
                        .then()
                            .assertThat()
                                .statusCode(200)
                            .extract().as(Book.class);

        Book bookFromAllBooksResponse = booksService
                .getAllBooksWithoutPagination()
                .then()
                .extract()
                .path("find{it.bookId == 51}");

        assertEquals(book, bookFromAllBooksResponse);
    }
}
