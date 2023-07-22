package runner;
import io.cucumber.java.BeforeAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@CucumberOptions(tags = "", features = {"src/test/resources/features/addProductToCart.feature"}, glue = {"classpath:stepDefinitions"},
        plugin = {"summary","pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumberreport.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests{
        @BeforeAll
        public void deleteAllureResults() throws IOException {
                FileUtils.deleteDirectory(new File("allure-results"));
        }
}
