package tests;

import business.ApiVerifier;
import listeners.LogListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import service.book.DeleteBookService;
import service.book.GetAllBooksService;
import service.book.GetBookByIdService;

import static utils.BookStatusCodes.DELETED;
import static utils.BookStatusCodes.NOT_FOUND;

public class DeleteBookTests extends BaseTests{

    @Test
    public void checkBookWithExistedIdDeleted(){
        DeleteBookService deleteBookAPI = new DeleteBookService(existedBookId);
        deleteBookAPI.call();

        new ApiVerifier(deleteBookAPI)
                .assertStatusCodeIs(DELETED.getCode());

        GetBookByIdService getBookByIdAPI = new GetBookByIdService(existedBookId);
        getBookByIdAPI.call();

        new ApiVerifier(getBookByIdAPI)
                .assertStatusCodeIs(NOT_FOUND.getCode());
    }

    @Test
    public void checkDeleteWithNotExistedBookId(){
        int bookId = GetAllBooksService.getMaxBookId() + 1;

        DeleteBookService deleteBookAPI = new DeleteBookService(existedBookId);
        deleteBookAPI.call();

        new ApiVerifier(deleteBookAPI)
                .assertStatusCodeIs(NOT_FOUND.getCode())
                .assertResponseBodyAttributeValue("error", NOT_FOUND.getError())
                .assertResponseBodyAttributeValue("errorMessage", String.format(NOT_FOUND.getErrorMessage(), bookId));
    }
}
