package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static endpoints.APIEndpoints.CREATE_DOC;

public class CreateDocPartner {
    public static Performable withCreateDocBody(Map<String, Object> docBody) {
        return Task.where("{0} create doc", actor ->
        {
            actor.attemptsTo(
                    Post.to(CREATE_DOC).with(
                            request -> {
                                request.contentType(ContentType.JSON);
                                request.header("x-api-key", "ZmRmNzRiNjgtMmVmZC00MGM4LWE3ZTYtNTFkYzhjMDVjNWUw");
                                request.body(docBody);
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
