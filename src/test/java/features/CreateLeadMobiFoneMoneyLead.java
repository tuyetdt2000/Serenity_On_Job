package features;

import com.github.javafaker.Faker;
import models.InitLeadRequest;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import questions.BodyResponse;
import tasks.InitLead;

import java.util.Locale;


@ExtendWith(SerenityJUnit5Extension.class)
public class CreateLeadMobiFoneMoneyLead {
    private Actor tuyet;

    @BeforeEach
    public void setUp() {
        tuyet = Actor.named("tuyet")
                .whoCan(CallAnApi.at("https://edp-gateway-server-uat.mobifi.vn"));
    }

    // ----------- SUCCESS CASE -----------
    @Test
    public void ValidData_ShouldSuccess() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123", "SenID", "MDS", randomPhoneNumber()
        )
                .programCode("")
                .fullName("Tên khách hàng")
                .email("")
                .gender("")
                .marriageStatus("")
                .build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isTrue(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(200),
                Ensure.that(BodyResponse.bodyResponse("data.contractCode").asString()).contains("PASO_"),
                Ensure.that(BodyResponse.bodyResponse("data.url").asString()).isNotEmpty()
        );
    }

    // ----------- requestId -----------
    @Test
    public void RequestIdNull_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                null, "SenID", "MDS", randomPhoneNumber())
                .build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field requestId not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }

    @Test
    public void RequestIdEmpty_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "",
                "SenID",
                "MDS",
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field requestId not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")

        );
    }

    @Test
    public void RequestIdTooLong_ShouldReturnError() {
        String longRequestId = generateLongString(256);
        InitLeadRequest request = new InitLeadRequest.Builder(
                longRequestId,
                "SenID",
                "MDS",
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field requestId not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }

    // ----------- productCode -----------
    @Test
    public void ProductCodeNull_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                null,
                "MDS",
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field productCode not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }

    @Test
    public void ProductCodeEmpty_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "",
                "MDS",
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field productCode not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }
    @Test
    public void ProductCodeInvalidLowerCase_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "senid",
                "MDS",
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280228 Field productCode invalid"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280228")
        );
    }

    // ----------- partnerCode -----------
    @Test
    public void PartnerCodeNull_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                null,
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field partnerCode not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }

    @Test
    public void PartnerCodeEmpty_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                "",
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field partnerCode not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }
    @Test
    public void PartnerCodeInvalidLowerCase_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                "mds",
                randomPhoneNumber()
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280228 Field partnerCode invalid"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280228")
        );
    }

    // ----------- telPhoneNumber -----------
    @Test
    public void PhoneNull_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                "MDS",
                null
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field telPhoneNumber not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }

    @Test
    public void PhoneEmpty_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                "MDS",
                ""
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280227 Field telPhoneNumber not null"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280227")
        );
    }

    @Test
    public void PhoneLessInvalidLength_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                "MDS",
                "09123456" // 9 số
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280228 Field telPhoneNumber invalid"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280228")
        );
    }

    @Test
    public void PhoneMoreInvalidLength_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                "MDS",
                "09123456722" // 11 số
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280228 Field telPhoneNumber invalid"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280228")
        );
    }

    @Test
    public void PhoneNonDigit_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                "REQ123",
                "SenID",
                "MDS",
                "09A2345678"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280228 Field telPhoneNumber invalid"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280228")
        );
    }


    // ----------- Logic duplicate lead -----------
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0032_LeadSourceIsTrue_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0359108399"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0032_LeadSourceIsFalse_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0359108099"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isTrue(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(200),
                Ensure.that(BodyResponse.bodyResponse("data.contractCode").asString()).contains("PASO_1755059786195"),
                Ensure.that(BodyResponse.bodyResponse("data.url").asString()).isNotEmpty()
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0033_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0985488481"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0035_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0919565656"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0037_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0912434343"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0034_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0369886074"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0036_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0389897879"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalFalse_BankIsExists_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0358154912"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalFalse_BankNotIsExists_ShouldReturnError() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0768485939"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }




    // ----------- Helpers -----------
    private static String randomPhoneNumber() {
        Faker f = new Faker(new Locale("vi"));
        return "09" + f.number().digits(8);
    }

    private static String generateLongString(int length) {
        return "R".repeat(Math.max(0, length));
    }

}
