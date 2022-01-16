package utils;

public enum BookStatusCodes {
    OK(200, "", ""),
    CREATED(201, "", ""),
    DELETED(204, "", ""),
    NOT_FOUND(404, "Not Found", "Book with 'bookId' = '%d' doesn't exist!"),
    CONFLICT(409, "Conflict", "Book with such 'bookId' already exists!")
    ;

    private final int code;
    private final String error;
    private final String errorMessage;


    BookStatusCodes(int code, String error, String errorMessage) {
        this.code = code;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public int getCode(){
        return code;
    }

    public String getError() {
        return error;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
