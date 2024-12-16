package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lead {

    public static Map<String, Object> leadbody(String phoneNumber) {
        Map<String, Object> data = new HashMap<>();

        // Populate the HashMap
        data.put("gender", "M");
        data.put("employer_ward", "D430748C9BB20186E0530A2518ABFC07");
        data.put("employer_name", "OTHER");
        data.put("permanent_address", "a b");
        data.put("current_ward", "D430748C9BB20186E0530A2518ABFC07");
        data.put("current_province", "30dd34a3031146238c5bd6b136de9214");
        data.put("place_of_issue", "Cuc canh sat QLHC ve TTXH");
        data.put("employer_district", "34B108C4F51100D6E0530A2510AB2319");
        data.put("employer_status", "FT");
        data.put("date_of_issue", "11/09/2024");
        data.put("current_district", "34B108C4F51100D6E0530A2510AB2319");
        data.put("employer_province", "30dd34a3031146238c5bd6b136de9214");
        data.put("partner_code", "senid_pfm_ttk2");
        data.put("permanent_province", "30dd34a3031146238c5bd6b136de9214");
        data.put("email", "a@gmail.com");
        data.put("document_number", "037097007461");
        data.put("job_position", "POSH29");

        // Create and populate the document_additional list
        List<Map<String, String>> documentAdditional = new ArrayList<>();
        Map<String, String> document = new HashMap<>();
        document.put("document_number", "164607934");
        document.put("type", "NationalID");
        documentAdditional.add(document);
        data.put("document_additional", documentAdditional);

        data.put("ip_address", "2402:9d80:249:bd7f:6971:1e33:36c:5890");
        data.put("income_amount", "20000000");
        data.put("permanent_district", "34B108C4F51100D6E0530A2510AB2319");
        data.put("current_address", "a b");
        data.put("other_employer_name", "OTHER");
        data.put("employer_address", "a b");
        data.put("marital_status", "SINGLE");
        data.put("full_name", "Nguyễn Văn Trường");
        data.put("permanent_ward", "D430748C9BAF0186E0530A2518ABFC07");
        data.put("dob", "13/03/1997");
        data.put("phone_number", phoneNumber);
        data.put("request_id", "PASO_1731397168035_1731397318351");
        data.put("date_of_expiry", "13/03/2025");

        return data;
    }

    public static Map<String, Object> doclink(String app_id) {

        Map<String, Object> data = new HashMap<>();

        // Create and populate the doc_urls list
        List<Map<String, String>> docUrls = new ArrayList<>();

        // Add each document to the list
        Map<String, String> doc1 = new HashMap<>();
        doc1.put("url", "https://tpc.com/cmndmt.jpg");
        doc1.put("type", "CMND_MT");
        docUrls.add(doc1);

        Map<String, String> doc2 = new HashMap<>();
        doc2.put("url", "https://tpc.com/cmndms.jpg");
        doc2.put("type", "CMND_MS");
        docUrls.add(doc2);

        Map<String, String> doc3 = new HashMap<>();
        doc3.put("url", "https://tpc.com/liveness_frontal.jpg");
        doc3.put("type", "LIVENESS_FRONTAL");
        docUrls.add(doc3);

        Map<String, String> doc4 = new HashMap<>();
        doc4.put("url", "https://tpc.com/liveness.jpg");
        doc4.put("type", "LIVENESS");
        docUrls.add(doc4);

        // Add doc_urls to the main HashMap
        data.put("doc_urls", docUrls);

        // Add other key-value pairs
        data.put("app_id", app_id);
        data.put("request_id", "123456");

        return data;
    }

}
