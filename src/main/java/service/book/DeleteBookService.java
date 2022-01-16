package service.book;

import service.Service;
import utils.HttpOperations;

public class DeleteBookService extends Service {

    static final String API_PATH = "/book/%d";

    public DeleteBookService(int bookId) {
        super(HttpOperations.DELETE, String.format(API_PATH, bookId));
        init();
    }
}
