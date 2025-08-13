package models;

public class InitLeadResponse {
    private boolean status;
    private String message;
    private int httpCode;
    private String errorCode;
    private Data data; // Có thể null nếu lỗi

    // Inner class cho phần "data"
    public static class Data {
        private String contractCode;
        private String url;

        public String getContractCode() { return contractCode; }
        public void setContractCode(String contractCode) { this.contractCode = contractCode; }

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

    // Getters và Setters
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getHttpCode() { return httpCode; }
    public void setHttpCode(int httpCode) { this.httpCode = httpCode; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
}
