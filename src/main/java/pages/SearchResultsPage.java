package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class SearchResultsPage {
    private WebDriver driver;
    private By searchResult(int resultNumber){
        return By.xpath("//ul[contains(@class,'content grid')]/following-sibling::li["+resultNumber+"]//div[contains(@class,'product__content')]/a");
    }
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    public ProductDetailsPage openResult(int resultNumber){
        new ElementActions(driver).click(searchResult(resultNumber));
        if (driver.getCurrentUrl().contains("Search-Results")){
            new ElementActions(driver).click(searchResult(resultNumber));
        }
        return new ProductDetailsPage(driver);
    }
}
