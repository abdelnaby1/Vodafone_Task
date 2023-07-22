package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.testng.Assert.*;

public class ElementActions {
    private  WebDriver driver;
    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    public static void click(WebDriver driver, By elementLocator) {
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
                        driver.findElement(elementLocator));
            } catch (Exception rootCauseException) {
                rootCauseException.initCause(exception);
                fail("Couldn't click on the element:" + elementLocator, rootCauseException);
            }
        }
    }

    public ElementActions click(By elementLocator) {
        click(driver, elementLocator);
        return this;
    }
    public static void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
        try {
            if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
                driver.findElement(elementLocator).clear();
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
                            driver.findElement(elementLocator));
                }
            } else {
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
                    String currentValue = driver.findElement(elementLocator).getAttribute("value");
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].setAttribute('value', '" + currentValue + text + "')",
                            driver.findElement(elementLocator));
                }
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    public ElementActions type(By elementLocator, String text) {
        type(driver, elementLocator, text, true);
        return this;
    }
    public String getText(By elementLocator) {
        return getText(driver,elementLocator);
    }
    public static String getText(WebDriver driver, By elementLocator) {
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String text;
        try {
            text =  driver.findElement(elementLocator).getText();
            return text;
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }
}
