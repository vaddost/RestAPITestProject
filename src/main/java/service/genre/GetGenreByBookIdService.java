package service.genre;

import service.Service;
import utils.HttpOperations;

public class GetGenreByBookIdService extends Service {

    static final String API_PATH = "/book/%d/genre";

    public GetGenreByBookIdService(int bookId) {
        super(HttpOperations.GET, String.format(API_PATH, bookId));
        init();
        disableLogging();
    }

    public int getGenreId(){
        return extractResponsePropertyAsInt("genreId");
    }
}
