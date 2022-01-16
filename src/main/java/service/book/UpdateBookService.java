package service.book;

import models.Book;
import service.Service;
import utils.HttpOperations;

public class UpdateBookService extends Service {

    static final String API_PATH = "/book";
    public UpdateBookService(Book body) {
        super(HttpOperations.PUT, API_PATH);
        init();
        setBody(body);
    }
}
