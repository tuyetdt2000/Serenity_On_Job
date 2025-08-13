package questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.InitLeadResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LastResponse implements Question<InitLeadResponse> {

    @Override
    public InitLeadResponse answeredBy(Actor actor) {
        try {
            String json = SerenityRest.lastResponse().asString();
            return new ObjectMapper().readValue(json, InitLeadResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response", e);
        }
    }

    public static LastResponse details() {
        return new LastResponse();
    }
}

