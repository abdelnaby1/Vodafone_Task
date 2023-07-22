package Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.SearchResultsPage;
import utils.ElementActions;

import java.time.Duration;

public class Navbar {
    private WebDriver driver;
    private By searchField = By.id("search-q");
    private By cartCountLoc = By.id("cartCountMini");

//    private By searchResult = By.xpath("(//*[contains(@class,'searchSection-searchResult-searchItem')])[1]/a");
    private By searchBtn = By.id("searchBtn");
    public Navbar(WebDriver driver) {
        this.driver = driver;
    }
    public String getCartCount(){
        String text = new  ElementActions(driver).getText(cartCountLoc);
        return  text;
    }
    public SearchResultsPage searchFor(String searchTerm){
        new ElementActions(driver)
                .type(searchField,searchTerm)
                .click(searchBtn);
        return new SearchResultsPage(driver);
    }
    public void refresh(){
        driver.navigate().refresh();
    }
}
