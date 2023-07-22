package APITests;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ZIPCodeTests {
    @DataProvider(name = "codes")
    public static Object[][] codes(){
        return new Object[][]{
                {"33939",true},
                {"33939000",false},
        };
    }
    @Test(dataProvider = "codes")
    public void checkZipCodes(String code,boolean isValid){
        var res =
                given()
                        .when()
                        .get("https://api.zippopotam.us/us/"+code);
        if (isValid){
            res.then().assertThat()
                    .statusCode(200)
                    .and()
                    .body("'post code'",Matchers.equalTo(code))
                    .and()
                    .body("places", Matchers.hasSize(Matchers.greaterThan(0)));

        }else{
            res.then().assertThat()
                    .statusCode(404);

        }
    }
}
