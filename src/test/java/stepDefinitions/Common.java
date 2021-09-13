package stepDefinitions;

import Utils.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.Dimension;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class Common implements En {
    public Common() {
        Given("Open Application", () -> {
            open(Objects.requireNonNull(Config.appConfig.get("appURL")));
            // Setting up window size to override default 800x600 in docker browser to avoid Element not found issue
            webdriver().driver().getWebDriver().manage().window().setSize(new Dimension(1200, 900));
            Pages.Git.pageShouldLoaded();
            Assert.assertTrue(Objects.requireNonNull(Selenide.title()).contains("GitHub"));
        });
    }
}