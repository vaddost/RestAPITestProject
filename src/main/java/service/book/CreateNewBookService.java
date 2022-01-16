package service.book;

import models.Book;
import service.Service;
import utils.HttpOperations;

public class CreateNewBookService extends Service {
    static final String API_PATH = "/book/";

    public CreateNewBookService(int authorId, int genreId, Book body) {
        super(HttpOperations.POST, API_PATH + authorId + "/" + genreId);
        init();
        setBody(body);
    }
}
