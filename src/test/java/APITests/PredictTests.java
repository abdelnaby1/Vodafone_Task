package APITests;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PredictTests {
    @DataProvider(name = "names")
    public static Object[][] names(){
        return new Object[][]{
                {"ahmed",true},
                {"john",true},
                {"xyziiii",false},
                {"oikjhb",false}
        };
    }
    @Test(dataProvider = "names")
    public void checkPersonAgeBasedOnName(String name,boolean isValid){
        var res =
                given()
                .queryParam("name",name)
                .when()
                .get("https://api.agify.io/");
        if (isValid){
            res.then().assertThat()
                    .statusCode(200)
                    .and()
                    .body("age", Matchers.notNullValue())
                    .and()
                    .body("count",Matchers.greaterThan(0));
        }else{
            res.then().assertThat()
                    .statusCode(200)
                    .and()
                    .body("age", Matchers.nullValue())
                    .and()
                    .body("count",Matchers.equalTo(0));
        }
    }
    @Test
    public void checkPersonAgeWithoutName(){
        given()
                .when()
                .queryParam("invalidname","xyz")
                .get("https://api.agify.io/")
                .then()
                .assertThat()
                .statusCode(422);

    }
    @Test
    public void checkPersonAgeWithInvalidQueryParam(){
        given()
                .when()
                .get("https://api.agify.io/")
                .then()
                .assertThat()
                .statusCode(422);

    }
    @Test(dataProvider = "names")
    public void checkPersonNationalityBasedOnName(String name,boolean isValid){
        var res =
                given()
                        .queryParam("name",name)
                        .when()
                        .get("https://api.nationalize.io/");
        if (isValid){
            res.then().assertThat()
                    .statusCode(200)
                    .and()
                    .body("count",Matchers.greaterThan(0))
                    .and()
                    .body("country", Matchers.hasSize(Matchers.greaterThan(0)));


        }else{
            res.then().assertThat()
                    .statusCode(200)
                    .and()
                    .body("count",Matchers.equalTo(0))
                    .and()
                    .body("country", Matchers.hasSize(0));        }
    }
    @Test
    public void checkPersonNationalityWithoutName(){
        given()
                .when()
                .queryParam("invalidname","xyz")
                .get("https://api.nationalize.io/")
                .then()
                .assertThat()
                .statusCode(422);

    }
    @Test
    public void checkPersonNationalityWithInvalidQueryParam(){
        given()
                .when()
                .get("https://api.nationalize.io/")
                .then()
                .assertThat()
                .statusCode(422);

    }

}
