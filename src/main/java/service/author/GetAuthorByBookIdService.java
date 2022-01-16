package service.author;

import service.Service;
import utils.HttpOperations;

public class GetAuthorByBookIdService extends Service {

    static final String API_PATH = "/book/%d/author";

    public GetAuthorByBookIdService(int bookId) {
        super(HttpOperations.GET, String.format(API_PATH, bookId));
        init();
        disableLogging();
    }

    public int getAuthorId(){
        return extractResponsePropertyAsInt("authorId");
    }
}
