package features;

import com.github.javafaker.Faker;
import models.Lead;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import questions.BodyResponse;
import tasks.CreateLeadPartner;

import java.util.Locale;

import static endpoints.APIEndpoints.BASE_URL;


@ExtendWith(SerenityJUnit5Extension.class)
public class CreateLeadPartnerStory {
    private Actor tuyet;

    @BeforeEach
    public void setUp(){
        tuyet = Actor.named("tuyet")
                .whoCan(CallAnApi.at(BASE_URL));
    }
    @Test
    public void createLeadPartner() {
        tuyet.attemptsTo(
                CreateLeadPartner.withLeadBody(Lead.leadbody(randomPhoneNumber())),
                Ensure.that(BodyResponse.bodyResponse("status").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("Create lead successful"),
                Ensure.that(BodyResponse.bodyResponse("data.kyc_flow").asString()).isEqualTo("flow2"),
                Ensure.that(BodyResponse.bodyResponse("data.app_id").asString()).isNotEmpty()
        );
    }

    public static String randomPhoneNumber() {
        Faker f = new Faker(new Locale("vi"));
        return "09" + f.number().digits(8);
    }
}
