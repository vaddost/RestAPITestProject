package tests;

import io.restassured.http.ContentType;
import models.Book;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class CreateNewBookTests extends BaseTests{

    private int authorId;
    private int genreId;

    @BeforeTest
    @Parameters({"authorId", "genreId"})
    public void testSetUp(int authorId, int genreId){
        this.authorId = authorId;
        this.genreId = genreId;
    }

    @Test(dataProvider = "new_book_test_data")
    public void checkIfNewBookCanBeCreated(Book book){
        int bookId = booksService.generateNewBookId();
        book.setBookId(bookId);
        Book actualBook = booksService.createNewBook(book, authorId, genreId)
                .then()
                    .assertThat()
                        .statusCode(HttpStatus.SC_CREATED)
                        .contentType(ContentType.JSON)
                .extract()
                    .as(Book.class);

        assertEquals(actualBook, book);

        Book createdBook = booksService.getBookById(bookId)
                .then()
                    .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .contentType(ContentType.JSON)
                    .extract()
                        .as(Book.class);

        assertEquals(createdBook, book);
    }

    @Test(dataProvider = "new_book_test_data")
    public void checkNewBookWithExistedIdNotCreated(Book book){
        book.setBookId(existedBookId);
        booksService.createNewBook(book, authorId, genreId)
                .then()
                    .assertThat()
                        .statusCode(HttpStatus.SC_CONFLICT)
                        .body("error", equalTo("Conflict"))
                        .body("errorMessage", equalTo("Book with such 'bookId' already exists!"));
    }
}
