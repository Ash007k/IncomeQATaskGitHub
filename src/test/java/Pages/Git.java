package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Git {

    private final static String searchSelector = "input[name=q]";

    public static void pageShouldLoaded() {
        $(searchSelector).should(exist);
    }

    public static void search(String input) {
        $(searchSelector).sendKeys(input);
        $(searchSelector).sendKeys(Keys.ENTER);
    }

    public static void openUserRepositoryPage(String username) {
        $$("nav[class*=menu] a").find(text("User")).should(exist).click();
        $("#user_search_results").find(By.linkText(username)).should(exist).click();
        $$(".UnderlineNav-item").find(text("Repositories")).should(exist).click();
    }

    public static void filterUserRepository(String reponame) {
        $("#your-repos-filter").should(exist).sendKeys(reponame);
        $("#user-repositories-list").should(exist).find(By.linkText(reponame)).click();
    }

    public static String getUIStarCountCurrentRepo() {
        return $(".social-count.js-social-count").should(exist).getText();
    }
}