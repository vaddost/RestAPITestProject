package service.book;

import service.Service;
import utils.HttpOperations;

public class GetBookByIdService extends Service {

    static final String API_PATH = "/book/";

    public GetBookByIdService(int bookId) {
        super(HttpOperations.GET, API_PATH + bookId);
        init();
    }
}
