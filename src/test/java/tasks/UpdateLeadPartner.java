package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static endpoints.APIEndpoints.UPDATE_LEAD;

public class UpdateLeadPartner {
    public static Performable withUpdateLeadBody(Map<String, Object> updateLead) {
        return Task.where("{0} create doc", actor ->
        {
            actor.attemptsTo(
                    Post.to(UPDATE_LEAD).with(
                            request -> {
                                request.contentType(ContentType.JSON);
                                request.header("x-api-key", "ZmRmNzRiNjgtMmVmZC00MGM4LWE3ZTYtNTFkYzhjMDVjNWUw");
                                request.body(updateLead);
                                request.then().log().status();
                                request.then().log().body();
                                request.that().log().body();
                                return request;
                            }
                    )
            );
        });
    }
}
