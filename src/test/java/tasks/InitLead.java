package tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.InitLeadRequest;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class InitLead implements Task {
    private final InitLeadRequest requestData;

    public InitLead(InitLeadRequest requestData) {
        this.requestData = requestData;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            String jsonBody = new ObjectMapper().writeValueAsString(requestData);

            actor.attemptsTo(
                    Post.to("/dob/mds/lead/init")
                            .with(request -> request
                                    .header("x-api-key", "MDdhNGQ1YTQtMDQ3NS00YWYxLThlNjEtMGYyYWNmZjc1OGQ4")
                                    .contentType("application/json")
                                    .body(jsonBody)
                            )
            );

            SerenityRest.lastResponse().prettyPrint();

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting request object to JSON", e);
        }
    }

    public static InitLead with(InitLeadRequest requestData) {
        return instrumented(InitLead.class, requestData);
    }

}
