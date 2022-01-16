package tests;

import business.ApiVerifier;
import listeners.LogListener;
import models.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import service.author.GetAuthorByBookIdService;
import service.book.CreateNewBookService;
import service.book.GetBookByIdService;
import service.genre.GetGenreByBookIdService;

import static utils.BookStatusCodes.*;

public class CreateNewBookTests extends BaseTests{

    private int authorId;
    private int genreId;

    @BeforeMethod
    public void testSetUp(){
        GetAuthorByBookIdService getAuthorByBookIdAPI = new GetAuthorByBookIdService(existedBookId);
        getAuthorByBookIdAPI.call();

        GetGenreByBookIdService getGenreByBookIdAPI = new GetGenreByBookIdService(existedBookId);
        getGenreByBookIdAPI.call();

        this.authorId = getAuthorByBookIdAPI.getAuthorId();
        this.genreId = getGenreByBookIdAPI.getGenreId();
    }

    @Test(dataProvider = "new_book_test_data")
    public void checkIfNewBookCanBeCreated(Book book) {

        CreateNewBookService createNewBookAPI = new CreateNewBookService(authorId, genreId, book);
        createNewBookAPI.call();

        new ApiVerifier(createNewBookAPI)
                .assertStatusCodeIs(CREATED.getCode())
                .assertResponseBodyEqualsObject(book);

        GetBookByIdService getBookByIdAPI = new GetBookByIdService(book.getBookId());
        getBookByIdAPI.call();



        new ApiVerifier(getBookByIdAPI)
                .assertStatusCodeIs(OK.getCode())
                .assertResponseBodyEqualsObject(createNewBookAPI.extractResponseBodyAsObject(Book.class));
    }

    @Test(dataProvider = "update_book_test_data")
    public void checkNewBookIsNotCreatedWithExistedId(Book book){
        CreateNewBookService createNewBookAPI = new CreateNewBookService(authorId, genreId, book);
        createNewBookAPI.call();

        new ApiVerifier(createNewBookAPI)
                .assertStatusCodeIs(CONFLICT.getCode())
                .assertResponseBodyAttributeValue("statusCode", CONFLICT.getCode())
                .assertResponseBodyAttributeValue("error", CONFLICT.getError())
                .assertResponseBodyAttributeValue("errorMessage", CONFLICT.getErrorMessage());
    }
}
