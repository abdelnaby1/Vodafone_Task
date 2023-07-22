package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementActions;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private WebDriverWait wait;
    private By continueShoppingBtn = By.xpath("//div[contains(@class,'shopingCartContainer-promoCode')]/button");
    private By cartItemsCountLoc = By.cssSelector(".shopingCartItemCont ul");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage continueShopping(){
        new ElementActions(driver).click(continueShoppingBtn);
        return new HomePage(driver);

    }
    public int getCartItemsCount(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItemsCountLoc));
        return driver.findElements(cartItemsCountLoc).size();
    }
}
