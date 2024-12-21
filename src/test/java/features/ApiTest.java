package features;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.example.Lead;
import org.example.LeadResponse;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Locale;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


@ExtendWith(SerenityJUnit5Extension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest {


    @Test
    public void createLeadPartnerTest() {

        Actor sam = Actor.named("Sam the supervisor")
                .whoCan(CallAnApi.at("https://testdg.vpbank.com.vn"));


        sam.attemptsTo(
                Post.to("/jvapi/jv-cus/create-lead-partner").with(
                        request -> request
                                .contentType(ContentType.JSON)
                                .header("x-api-key", "ZmRmNzRiNjgtMmVmZC00MGM4LWE3ZTYtNTFkYzhjMDVjNWUw")
                                .body(Lead.leadbody(randomPhoneNumber()))
                )
        );

        LeadResponse leadResponse = SerenityRest.lastResponse().as(LeadResponse.class);

        sam.attemptsTo(
                Post.to("/jvapi/jv-cus/receive-document-link").with(
                        request -> request
                                .contentType(ContentType.JSON)
                                .header("x-api-key", "ZmRmNzRiNjgtMmVmZC00MGM4LWE3ZTYtNTFkYzhjMDVjNWUw")
                                .body(Lead.doclink("leadResponse.getData().getApp_id()"))
                )
        );

        sam.should(
                seeThatResponse("all the expected users should be returned",
                        response -> response.statusCode(200)
                                .body("message", equalTo("receive document link successfully")))
        );
    }

    public static String randomPhoneNumber() {
        Faker f = new Faker(new Locale("vi"));
        return "09" + f.number().digits(8);
    }
}
