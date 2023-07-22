package APITests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTests {
    @Test
    public void checkGetRandomUser(){
        given()
                .when()
                .get("https://randomuser.me/api/")
                .then()
                .statusCode(200)
                .and()
                .time(Matchers.lessThan(1500L))
                .and()
                .body("results[0].gender",Matchers.oneOf("female","male"))
                .and()
                .body("results[0].name",Matchers.hasKey("title"))
                .and()
                .body("results[0].name",Matchers.hasKey("first"))
                .and()
                .body("results[0].name",Matchers.hasKey("last"));

    }
}
