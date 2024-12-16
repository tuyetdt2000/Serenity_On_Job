package org.example;

public class LeadResponse {

    // composite
    private String status;
    private String message;
    private String time;
    private Data data;

    @lombok.Data
    public static class Data {
        private String kyc_flow;
        private String app_id;
    }
}
