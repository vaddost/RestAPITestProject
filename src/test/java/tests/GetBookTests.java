package tests;

import business.ApiVerifier;
import listeners.LogListener;
import models.Book;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import service.book.GetAllBooksService;
import service.book.GetBookByIdService;

import static utils.BookStatusCodes.*;

public class GetBookTests extends BaseTests{

    @Test
    public void checkGetBookWithExistedId(){
        GetBookByIdService getBookByIdAPI = new GetBookByIdService(existedBookId);
        getBookByIdAPI.call();

        GetAllBooksService getAllBooksAPI = new GetAllBooksService();
        getAllBooksAPI.disablePagination();
        getAllBooksAPI.call();
        Book book = getAllBooksAPI.getBookWithId(existedBookId);

        new ApiVerifier(getBookByIdAPI)
                        .assertStatusCodeIs(OK.getCode())
                        .assertResponseBodyEqualsObject(book);
    }

    @Test
    public void checkGetBookWithInvalidId(){
        int bookId = -1;
        GetBookByIdService getBookByIdAPI = new GetBookByIdService(bookId);
        getBookByIdAPI.call();

        new ApiVerifier(getBookByIdAPI)
                        .assertResponseBodyAttributeValue("statusCode", NOT_FOUND.getCode())
                        .assertResponseBodyAttributeValue("error", NOT_FOUND.getError())
                        .assertResponseBodyAttributeValue("errorMessage",
                                String.format(NOT_FOUND.getErrorMessage(), bookId));
    }
}
