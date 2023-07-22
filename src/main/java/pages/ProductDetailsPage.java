package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

import java.time.Duration;

public class ProductDetailsPage {
    private WebDriver driver;

    private By addToBasketBtn = By.xpath("//div[contains(@class,'addToBasket-btn')]/button[not(contains(@id,'pickFromStoreBtn'))]");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public CartPage addToCart(){
        new ElementActions(driver).click(addToBasketBtn);
        if(driver.getCurrentUrl().contains("productDetails")){
            new ElementActions(driver).click(addToBasketBtn);
        }
        return new CartPage(driver);
    }
    public CartPage addToCartFromSearch(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new ElementActions(driver).click(addToBasketBtn);
        return new CartPage(driver);
    }
}
