package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import Components.Navbar;
import pages.CartPage;
import pages.HomePage;

import java.nio.file.Path;
import java.util.Optional;


public class AddToCartStepDefinition {
    private WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

    }
    @Given("User on vodafone shop home page {string}")
    public void user_on_vodafone_shop_home_page(String url) {
        new HomePage(driver).navigate(url);
    }
    @When("The user add product number {int} to the cart")
    public void the_user_add_product_to_cart(int productNumber) {
        new HomePage(driver)
                .openProductDetails(productNumber)
                .addToCart();
    }
    @And("The user continue shopping and add product number {int} to the cart")
    public void the_user_continue_shopping_and_add_product_to_cart(int productNumber) {
        new CartPage(driver).continueShopping()
                .openProductDetails(productNumber)
                .addToCart();
    }
    @And("The user search for {string} and add it to the cart")
    public void the_user_search_for_product_and_add_it_to_cart(String searchTerm) {
       new Navbar(driver)
               .searchFor(searchTerm)
               .openResult(2)
               .addToCart();
    }
    @Then("The three products should existed on the cart")
    public void the_use_should_redirected_to_cart_page(){
        Assert.assertEquals(new CartPage(driver).getCartItemsCount(),3);
    }
    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());}

      if(driver != null){
          driver.quit();
      }
    }
}
