package tests;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import service.BooksService;
import utils.JsonToObjectParser;
import utils.PropertiesReader;

import java.io.FileInputStream;
import java.util.Iterator;

public class BaseTests {
    PropertiesReader propertiesReader;
    BooksService booksService;
    int existedBookId;

    @BeforeClass
    @Parameters({"existedBookId"})
    public void setUp(int existedBookId) {
        propertiesReader = PropertiesReader.getPropertiesReader();
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(propertiesReader.getBaseUri())
                .setBasePath(propertiesReader.getBasePath())
                .setContentType(ContentType.JSON)
                .build();
        booksService = new BooksService(requestSpecification);

        this.existedBookId = existedBookId;
    }

    @DataProvider(name = "new_book_test_data")
    public Object[][] getNewBook(){
        JsonToObjectParser<Book> jsonToObjectParser = new JsonToObjectParser<>();
        Book newBook = jsonToObjectParser.parseJsonFileToObject("src/main/resources/book.json", Book.class);
        return new Object[][]{
                {
                    newBook
                }
        };
    }
}
