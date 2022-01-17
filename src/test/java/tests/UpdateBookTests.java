package tests;

import business.ApiVerifier;
import models.Book;
import org.testng.annotations.Test;
import service.book.GetAllBooksService;
import service.book.GetBookByIdService;
import service.book.UpdateBookService;

import static utils.BookStatusCodes.*;

public class UpdateBookTests extends BaseTests{

    @Test(dataProvider = "update_book_test_data")
    public void checkBookAttributesCanBeUpdated(Book book){
        UpdateBookService updateBookAPI = new UpdateBookService(book);
        updateBookAPI.call();

        new ApiVerifier(updateBookAPI)
                .assertStatusCodeIs(OK.getCode())
                .assertResponseBodyEqualsObject(book);

        GetBookByIdService getBookByIdAPI = new GetBookByIdService(book.getBookId());
        getBookByIdAPI.disableLogging();
        getBookByIdAPI.call();

        new ApiVerifier(getBookByIdAPI)
                .assertStatusCodeIs(OK.getCode())
                .assertResponseBodyEqualsObject(updateBookAPI.extractResponseBodyAsObject(Book.class));

    }

    @Test(dataProvider = "new_book_test_data")
    public void checkUpdateWithNotExistedId(Book book){
        int bookId = GetAllBooksService.getMaxBookId() + 1;
        UpdateBookService updateBookAPI = new UpdateBookService(book);
        updateBookAPI.call();

        new ApiVerifier(updateBookAPI)
                .assertStatusCodeIs(NOT_FOUND.getCode())
                .assertResponseBodyAttributeValue("error", NOT_FOUND.getError())
                .assertResponseBodyAttributeValue("errorMessage",
                        String.format(NOT_FOUND.getErrorMessage(), bookId));
    }
}
