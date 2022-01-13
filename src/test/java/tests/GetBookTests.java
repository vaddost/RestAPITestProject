package tests;

import models.Book;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GetBookTests extends BaseTests{

    @Test
    public void checkGetBookWithExistedId(){
        Book book = booksService
                        .getBookById(existedBookId)
                        .then()
                            .assertThat()
                                .statusCode(200)
                            .extract().as(Book.class);

        Book bookFromAllBooksResponse = booksService
                        .getAllBooksWithoutPagination()
                        .then()
                            .assertThat()
                                .statusCode(200)
                            .extract()
                                .jsonPath()
                                .getObject("find{it.bookId == " + existedBookId + "}", Book.class);


        assertEquals(book, bookFromAllBooksResponse);
    }

    @Test
    public void checkGetBookWithInvalidId(){
        int bookId = -1;
        booksService.getBookById(bookId)
                .then()
                .assertThat()
                    .statusCode(404)
                    .body("statusCode", equalTo(404))
                    .body("error", equalTo("Not Found"))
                    .body("errorMessage", equalTo("Book with 'bookId' = '-1' doesn't exist!"));
    }
}
