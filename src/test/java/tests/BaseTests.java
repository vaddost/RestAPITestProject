package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import service.BooksService;
import utils.PropertiesReader;

public class BaseTests {
    PropertiesReader propertiesReader;
    BooksService booksService;
    @BeforeClass
    public void setUp() {
        propertiesReader = PropertiesReader.getPropertiesReader();
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(propertiesReader.getBaseUri())
                .setBasePath(propertiesReader.getBasePath())
                .build();
        booksService = new BooksService(requestSpecification);
    }
}
