package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static endpoints.APIEndpoints.CREATE_LEAD;

public class CreateLeadPartner {
    public static Performable withLeadBody(Map<String, Object> leadBody) {
        return Task.where("{0} create lead partner", actor ->
        {
            actor.attemptsTo(
                    Post.to(CREATE_LEAD).with(
                            request -> {
                                request.contentType(ContentType.JSON);
                                request.header("x-api-key", "ZmRmNzRiNjgtMmVmZC00MGM4LWE3ZTYtNTFkYzhjMDVjNWUw");
                                request.body(leadBody);
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
