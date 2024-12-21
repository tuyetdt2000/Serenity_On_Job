package tasks;

import endpoints.APIEndpoints;
import models.LoginRequest;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginTask implements Task {
    private final LoginRequest loginRequest;

    public LoginTask(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .header("Accept", "application/json")
                .header("Accept-Language", "vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5")
                .header("Access-Control-Allow-Origin", "*")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Origin", "http://192.168.20.73:4604")
                .header("Referer", "http://192.168.20.73:4604/")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
                .body(loginRequest)
                .post(APIEndpoints.BASE_URL + APIEndpoints.LOGIN);
    }

    public static LoginTask withCredentials(String userName, String password) {
        return instrumented(LoginTask.class, new LoginRequest(userName, password));
    }
}
