package APITests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class IPTests {
    String ip;
    @Test
    public void checkGetCurrentIP(){
        var res = given()
                .queryParam("format","json")
                .when()
                .get("https://api.ipify.org/");
         res.then()
                .statusCode(200)
                .and()
                .body("ip", Matchers.not(Matchers.emptyString()))
                .and()
                .body("ip",Matchers.notNullValue());

        ip = res.jsonPath().getString("ip");
    }
    @Test(dependsOnMethods = {"checkGetCurrentIP"})
    public void checkGetIPInformation(){
        given()
              .when()
                .get("https://ipinfo.io/"+ip+"/geo")
                .then()
                .statusCode(200)
                .and()
                .body("ip", Matchers.equalTo(ip))
                .and()
                .body("city",Matchers.notNullValue())
                .and()
                .body("region",Matchers.notNullValue())
                .and()
                .body("country",Matchers.notNullValue());
    }
    @Test
    public void checkGetInvalidIPInformation(){
        given()
                .when()
                .get("https://ipinfo.io/InvalidIp/geo")
                .then()
                .statusCode(404)
                .and()
                .body("error.title",Matchers.equalTo("Wrong ip"));
    }
}
