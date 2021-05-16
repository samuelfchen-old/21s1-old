package SCD2_2021_T3;

import org.json.simple.JSONObject;

public class Response {
    private boolean success;
    private int code;
    private JSONObject message;
    private String reason;
    private String status;

    public Response(boolean success, String status, int code, String reason, JSONObject message) {
        this.success = success;
        this.status = status;
        this.code = code;
        this.reason = reason;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public String status() {
        return status;
    }

    public int code() {
        return code;
    }

    public String reason() {
        return reason;
    }

    public JSONObject message() {
        return message;
    }
}