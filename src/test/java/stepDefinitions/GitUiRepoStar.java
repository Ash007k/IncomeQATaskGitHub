package stepDefinitions;

import Services.Git;
import com.codeborne.selenide.Selenide;
import io.cucumber.java8.En;
import org.junit.Assert;

import java.util.Objects;

public class GitUiRepoStar implements En {
    ThreadLocal<Integer> apiStarValue = new ThreadLocal<>();
    ThreadLocal<String> uiStarValue = new ThreadLocal<>();

    public GitUiRepoStar() {
        When("Search the {string} user", (String userName) -> {
            Pages.Git.search(userName);
            Assert.assertTrue(Objects.requireNonNull(Selenide.title()).contains(userName));
        });

        And("Navigate to {string} users repository page", Pages.Git::openUserRepositoryPage);

        When("Search and Open {string} repository", Pages.Git::filterUserRepository);

        Then("Get and store Number of Stars for current repo", () -> uiStarValue.set(Pages.Git.getUIStarCountCurrentRepo()));

        Then("Call and store GitHub API Star value for {string} user and {string}", (String userName, String repoName) -> apiStarValue.set(Git.getRepoDetails(userName, repoName).getBody().jsonPath().getInt("stargazers_count")));

        Then("the both UI star and API should match with K - {string}", (String formatInK) -> {
            String apiStar = formatInK.equalsIgnoreCase("true") ? Math.round(apiStarValue.get()/1000.0) + "k" : String.valueOf(apiStarValue.get());
            Assert.assertEquals(apiStar, uiStarValue.get());
        });
    }
}