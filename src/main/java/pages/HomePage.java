package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class HomePage {
    private WebDriver driver;
    private String url = "https://eshop.vodafone.com.eg/shop/home";
    private By productLoc(int productNumber){
        return By.xpath("//a[contains(@id,'productCard0')]["+productNumber+"]");

    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("When i navigate to Home.")
    public HomePage navigate(String url){
        driver.get(url);
        return this;
    }
    @Attachment("And i open a Product.")
    public ProductDetailsPage openProductDetails(int productIndex){
        new ElementActions(driver).click(productLoc(productIndex));
        return new ProductDetailsPage(driver);
    }

}
