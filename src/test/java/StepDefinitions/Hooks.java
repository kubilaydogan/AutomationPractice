package StepDefinitions;

import Core.Driver;
import Core.Utilities.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    private static boolean setUpIsDone = false;
    private WebDriver driver;

    @Before(order=1, value="not @hookless")
    public void start() {
        if (!setUpIsDone) {
            log.info("===============================================================");
            log.info("|          Test is Starting...");
            log.info("|          Environment : " + ConfigurationReader.getProperty("env"));
            log.info("|          Operating System : " + System.getProperty("os.name"));
            log.info("|          Browser: " + ConfigurationReader.getProperty("browser"));
            log.info("|          URL: " + ConfigurationReader.getProperty("url"));
            log.info("|          Author: " + ConfigurationReader.getProperty("author"));
            log.info("===============================================================\n");
            setUpIsDone = true;
        }
    }

    @Before(order=2, value="not @hookless")
    public void start2(Scenario scenario) {
        log.info("===============================================================");
        log.info("|          Scenario Name: " + scenario.getName());
        log.info("===============================================================");
        driver = new Driver().getDriver();
    }

    @After("not @hookless")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
