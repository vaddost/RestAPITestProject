package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.HttpOperations;
import utils.PropertiesReader;

import static io.restassured.RestAssured.given;

public abstract class Service {
    RequestSpecification reqSpec;
    HttpOperations method;
    Response response;
    String url;

    public Service(HttpOperations method, String url){
        this.url = url;
        this.method = method;
        reqSpec = given();
    }

    protected void init(){
        PropertiesReader propertiesReader = PropertiesReader.getPropertiesReader();
        reqSpec.baseUri(propertiesReader.getBaseUri())
                .basePath(propertiesReader.getBasePath());
        reqSpec.contentType(ContentType.JSON);
    }

    public <T> void setBody(T body){
        reqSpec.body(body);
    }

    public void setParam(String name, String value){
        reqSpec.param(name, value);
    }

    public void setParam(String name, boolean value){
        reqSpec.param(name, value);
    }

    public int getResponseStatusCode(){
        return response.getStatusCode();
    }

    public String extractResponsePropertyAsString(String path){
        return response.jsonPath().getString(path);
    }

    public int extractResponsePropertyAsInt(String path){
        return response.jsonPath().getInt(path);
    }

    public <T> T extractResponseBodyAsObject(Class<T> clazz){
        return response.getBody().as(clazz);
    }

    public void disableLogging(){
        reqSpec.noFilters();
    }

    public void call(){
        switch (method){
            case GET:
                response = reqSpec.get(url);
                break;
            case POST:
                response = reqSpec.post(url);
                break;
            case PUT:
                response = reqSpec.put(url);
                break;
            case DELETE:
                response = reqSpec.delete(url);
                break;
        }
    }
}
