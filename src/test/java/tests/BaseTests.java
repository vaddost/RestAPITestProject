package tests;

import com.github.javafaker.Faker;
import models.Book;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import service.book.GetAllBooksService;
import utils.JsonToObjectParser;

public class BaseTests {
    int existedBookId;

    @BeforeTest
    public void setUp() {
        this.existedBookId = GetAllBooksService.getMaxBookId();
    }

    @DataProvider(name = "new_book_test_data")
    public Object[][] getNewBook(){
        JsonToObjectParser<Book> jsonToObjectParser = new JsonToObjectParser<>();
        Book newBook = jsonToObjectParser.parseJsonFileToObject("src/main/resources/book.json", Book.class);
        newBook.setBookId(GetAllBooksService.getMaxBookId() + 1);
        return new Object[][]{
                {
                    newBook
                }
        };
    }

    @DataProvider(name = "update_book_test_data")
    public Object[][] updateBookData(){
        GetAllBooksService getAllBooksAPI = new GetAllBooksService();
        getAllBooksAPI.call();

        Faker faker = new Faker();

        return new Object[][]{
                {
                    new Book().getBuilder()
                            .setBookId(getAllBooksAPI.getListOfBooksReturned().get(0).getBookId())
                            .setBookName(faker.book().title())
                            .setBookDescription(faker.lorem().fixedString(30))
                            .setBookLanguage("english")
                            .setPageCount(faker.number().numberBetween(100, 300))
                            .setSize(20.0, 20.2, 10.0)
                            .setPublicationYear(1975)
                            .build()
                }
        };
    }
}
