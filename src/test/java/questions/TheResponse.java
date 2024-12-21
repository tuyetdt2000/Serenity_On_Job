package questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import io.restassured.response.Response;
public class TheResponse {
    public static Question<String> token() {
        return actor -> SerenityRest.lastResponse().jsonPath().getString("token");
    }

    public static Question<Integer> statusCode() {
        return actor -> SerenityRest.lastResponse().statusCode();
    }
}

