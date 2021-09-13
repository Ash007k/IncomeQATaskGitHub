package stepDefinitions;

import Utils.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java8.En;
import org.junit.Assert;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;

public class Common implements En {
    public Common() {
        Given("Open Application", () -> {
            Configuration.startMaximized = true;
            open(Objects.requireNonNull(Config.appConfig.get("appURL")));
            Pages.Git.pageShouldLoaded();
            Assert.assertTrue(Objects.requireNonNull(Selenide.title()).contains("GitHub"));
        });
    }
}