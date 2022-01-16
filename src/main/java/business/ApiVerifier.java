package business;

import service.Service;

import static org.testng.Assert.assertEquals;

public class ApiVerifier {
    Service service;

    public ApiVerifier(Service service) {
        this.service = service;
    }

    public ApiVerifier assertStatusCodeIs(int statusCode){
        assertEquals(service.getResponseStatusCode(), statusCode);
        return this;
    }

    public <T> void assertResponseBodyEqualsObject(T expected){
        assertEquals(
                service.extractResponseBodyAsObject(expected.getClass()),
                expected
        );
    }

    public ApiVerifier assertResponseBodyAttributeValue(String attributePath, String expected){
        assertEquals(service.extractResponsePropertyAsString(attributePath), expected);
        return this;
    }

    public ApiVerifier assertResponseBodyAttributeValue(String attributePath, int expected){
        assertEquals(service.extractResponsePropertyAsInt(attributePath), expected);
        return this;
    }


}
