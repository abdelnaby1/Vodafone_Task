package APITests;

import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.cucumber.core.internal.com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static io.restassured.RestAssured.given;

public class BitcoinPriceIndexTests {
    @Test
    public void checkBPI(){
        var res = given()
                .when()
                .get("https://api.coindesk.com/v1/bpi/currentprice.json");
        res.then().assertThat().statusCode(200);

        String date = res.body().jsonPath().getString("time.updatedISO").substring(0,10);
        String isoString = Instant.now().toString();
        Assert.assertTrue(isoString.contains(date));
        res.then()
                .body("bpi",Matchers.hasKey("USD"))
                .and()
                .body("bpi",Matchers.hasKey("GBP"))
                .and()
                .body("bpi",Matchers.hasKey("EUR"));

    }

}
