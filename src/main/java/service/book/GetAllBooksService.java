package service.book;

import models.Book;
import service.Service;
import utils.HttpOperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllBooksService extends Service {

    static final String API_PATH = "/books";

    public GetAllBooksService() {
        super(HttpOperations.GET, API_PATH);
        init();
        disableLogging();
    }

    public List<Book> getListOfBooksReturned(){
        return Arrays.stream(extractResponseBodyAsObject(Book[].class)).collect(Collectors.toList());
    }

    public Book getBookWithId(int bookId){
        return getListOfBooksReturned()
                .stream()
                .filter(x -> x.getBookId() == bookId).findFirst()
                .orElseThrow();
    }

    public void disablePagination(){
        setParam("pagination", false);
    }

    public void orderByDesc(){
        setParam("orderType", "desc");
    }

    public void sortBy(String attributeName){
        setParam("sortBy", attributeName);
    }

    public static int getMaxBookId(){
        GetAllBooksService getAllBooksService = new GetAllBooksService();
        getAllBooksService.orderByDesc();
        getAllBooksService.sortBy("bookId");
        getAllBooksService.call();

        return getAllBooksService.getListOfBooksReturned().get(0).getBookId();
    }
}
