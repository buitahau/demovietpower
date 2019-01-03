package vietpower.com.model;

public class ResponseMessage {
    public static String RESPONSE_SUCCESS_TYPE = "success";
    public static String RESPONSE_ERROR_TYPE = "error";
    private String type;
    private String message;

    public ResponseMessage(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
