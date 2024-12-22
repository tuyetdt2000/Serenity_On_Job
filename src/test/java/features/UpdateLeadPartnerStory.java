package features;

import models.Lead;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import questions.BodyResponse;
import tasks.CreateDocPartner;
import tasks.CreateLeadPartner;
import tasks.UpdateLeadPartner;

import static endpoints.APIEndpoints.BASE_URL;
import static features.CreateLeadPartnerStory.randomPhoneNumber;


@ExtendWith(SerenityJUnit5Extension.class)
public class UpdateLeadPartnerStory {
    private Actor tuyet;

    @BeforeEach
    public void setUp(){
        tuyet = Actor.named("tuyet")
                .whoCan(CallAnApi.at(BASE_URL));
    }
    @Test
    public void updateLeadPartner() {
        // Biến để lưu app_id
        String appId;

        // Gửi request để tạo Lead và trích xuất app_id
        tuyet.attemptsTo(
                CreateLeadPartner.withLeadBody(Lead.leadbody(randomPhoneNumber())),
                Ensure.that(BodyResponse.bodyResponse("status").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("Create lead successful")
        );
        // Trích xuất app_id từ BodyResponse (Question)
        appId = tuyet.asksFor(BodyResponse.bodyResponse("data.app_id").asString());
        System.out.println("App ID: " + appId);

        // Sử dụng app_id trong CreateDocPartner
        tuyet.attemptsTo(
                CreateDocPartner.withCreateDocBody(Lead.doclink(appId)),
                Ensure.that(BodyResponse.bodyResponse("status").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("receive document link successfully")
        );
        // Sử dụng app_id trong UpdateLeadParter
        tuyet.attemptsTo(
                UpdateLeadPartner.withUpdateLeadBody(Lead.updateLead(appId,randomPhoneNumber())),
                Ensure.that(BodyResponse.bodyResponse("status").asString()).isEqualTo("invalid_parameter"),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("Không thể update do hồ sơ chưa submit los round 2!")
        );
    }

}
