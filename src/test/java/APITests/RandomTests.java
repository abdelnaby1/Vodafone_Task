package APITests;

import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RandomTests {
    @Test
    public void checkGetRandomJoke(){
        given()
                .when()
                .get("https://official-joke-api.appspot.com/random_joke")
                .then()
                .statusCode(200)
                .and()
                .body("setup",Matchers.not(Matchers.emptyString()))
                .and()
                .body("punchline",Matchers.not(Matchers.emptyString()));
    }
    @Test
    public void checkGetRandomFactLength(){
        var res =
                given()
                .when()
                .get("https://catfact.ninja/fact");
        res.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("fact",Matchers.notNullValue())
                .and()
                .body("length",Matchers.equalTo(res.jsonPath().getString("fact").length()));

    }
    @Test
    public void checkGetRandomActivity(){
        given()
                .when()
                .get("https://www.boredapi.com/api/activity")
                .then()
                .statusCode(200)
                .and()
                .body("activity",Matchers.not(Matchers.emptyOrNullString()))
                .and()
                .body("participants",Matchers.greaterThan(0));
    }
    @Test
    public void checkGetRandomDogImage(){
        given()
                .when()
                .get("https://dog.ceo/api/breeds/image/random")
                .then()
                .statusCode(200)
                .and()
                .body("status",Matchers.equalTo("success"))
                .and()
                .body("message",Matchers.startsWithIgnoringCase("https"))
                .and()
                .body("message",Matchers.endsWithIgnoringCase(".jpg"));

    }



}
