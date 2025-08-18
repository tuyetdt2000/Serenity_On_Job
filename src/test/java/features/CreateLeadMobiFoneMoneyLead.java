package features;

import com.github.javafaker.Faker;
import models.*;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import questions.BodyResponse;
import tasks.InitLead;
import tasks.MariaDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String randomPhone = randomPhoneNumber();
        InitLeadRequest request = new InitLeadRequest.Builder(
                randomPhone, "SenID", "MDS", randomPhone
        )
                .programCode(null)
                .fullName("Đoàn Thị Tuyết")
                .email("tuyetdt@gmail.com")
                .gender("F")
                .marriageStatus("SINGLE")
                .personalIdNo("001300031106")
                .issueDate("19/03/2025")
                .issuePlace("Bộ Công An")
                .oldPersonalIdNo("123456789")
                .hometownAddress(new HometownAddress(
                        "68/68 LÊ VĂN SỸ",
                        "phuong 13",
                        "quan phu Nhuan",
                        "Ho Chi Minh"
                ))
                .residenceAddress(new ResidenceAddress(
                        "68/68 LÊ VĂN SỸ",
                        "phuong 13",
                        "quan phu Nhuan",
                        "Ho Chi Minh"
                ))
                .ipAddress("171.252.41.37")
                .device(new Device(
                        "abc",
                        "Vietnam",
                        "Hanoi",
                        "Desktop",
                        "Windows 10",
                        "Chrome"
                ))
                .employerStatus("EMPL1")
                .jobPosition("POSH4")
                .employerName("CÔNG TY CP TẬP ĐOÀN ĐỨC HẠNH MARPHAVET")
                .employerAddress(new EmployerAddress(
                        "68/68 LÊ VĂN SỸ",
                        "phuong 13",
                        "quan phu Nhuan",
                        "Ho Chi Minh"
                ))
                .incomeAmount("15500000")
                .build();

        tuyet.attemptsTo(

                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isTrue(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(200),
                Ensure.that(BodyResponse.bodyResponse("data.contractCode").asString()).contains("PASO_"),
                Ensure.that(BodyResponse.bodyResponse("data.url").asString()).isNotEmpty()
        );
        System.out.println(randomPhone);
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
    public void Duplicate_InternalTrue_AppStatusMTT0032_LeadSourceIsTrue_InitMexAndCamTheSame_ShouldReturnSuccess() throws SQLException {
        String phone="0359108030";

        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                phone
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isTrue(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(200),
                Ensure.that(BodyResponse.bodyResponse("data.contractCode").asString()).contains("PASO_1755529793406"),
                Ensure.that(BodyResponse.bodyResponse("data.url").asString()).isNotEmpty()
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0032_LeadSourceIsTrue_InitMexAndCamNotTheSame_ShouldReturnError() throws SQLException {
        String phone="0359108031";

        // Update trước khi gọi API (ví dụ xóa hoặc chỉnh trạng thái)
        MariaDBUtils.executeUpdate(
                "UPDATE cus_contract_addition SET modified_time = NOW() WHERE cus_contract_id='16859'"
        );
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                phone
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isFalse(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).contains("#280230 Phone is exists"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(400),
                Ensure.that(BodyResponse.bodyResponse("errorCode").asString()).isEqualTo("280230")
        );
    }
    @Test
    public void Duplicate_InternalTrue_AppStatusMTT0032_LeadSourceIsFalse_ShouldReturnSuccess() {
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
    public void Duplicate_InternalTrue_AppStatusMTT0036_ShouldReturnSuccess() throws SQLException {
        String phone="0389897879";

        // Update trước khi gọi API (ví dụ xóa hoặc chỉnh trạng thái)
        MariaDBUtils.executeUpdate(
                String.format("UPDATE cus_contract SET modified_time = NOW(), status='MTT0036' " +
                        " WHERE tel_phone_number='%s'",phone)

        );
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                phone
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isTrue(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(200),
                Ensure.that(BodyResponse.bodyResponse("data.contractCode").asString()).contains("PASO_"),
                Ensure.that(BodyResponse.bodyResponse("data.url").asString()).isNotEmpty()
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
    public void Duplicate_InternalFalse_BankNotIsExists_ShouldReturnSuccess() {
        InitLeadRequest request = new InitLeadRequest.Builder(
                generateLongString(10),
                "SenID",
                "MDS",
                "0768485939"
        ).build();

        tuyet.attemptsTo(
                InitLead.with(request),
                Ensure.that(BodyResponse.bodyResponse("status").asBoolean()).isTrue(),
                Ensure.that(BodyResponse.bodyResponse("message").asString()).isEqualTo("success"),
                Ensure.that(BodyResponse.bodyResponse("httpCode").asInteger()).isEqualTo(200),
                Ensure.that(BodyResponse.bodyResponse("data.contractCode").asString()).contains("PASO_"),
                Ensure.that(BodyResponse.bodyResponse("data.url").asString()).isNotEmpty()
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
