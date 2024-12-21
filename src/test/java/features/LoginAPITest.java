package features;

import endpoints.APIEndpoints;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.annotations.Test;
import tasks.LoginTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginAPITest {
    private Actor actor;

    @BeforeEach
    public void setUp() {
        actor = Actor.named("API Tester").whoCan(CallAnApi.at(APIEndpoints.BASE_URL));
    }

    @Test
    public void shouldLoginSuccessfully() {
        actor.attemptsTo(
                LoginTask.withCredentials("tuyetdt20", "zvd9BF4UR4aXqxMF32CB3XIEqfRRS7J9t8JdotmnTN1WzSiorHytRq4gTJGecUFcOQyFl5sGvviDgGlO1GHd3tlYVaN1fUpzCKKh+ufA1rfOzGFwpBydXF424gbwp5fW2vmnkSDI+0+ONrJDc56QoeJflRph1mb6fYVf1CmMrkk=")
        );

        actor.should(
                seeThat("Response code", response -> SerenityRest.lastResponse().statusCode(), equalTo(200))
        );
    }
}
