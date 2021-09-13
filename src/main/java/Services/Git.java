package Services;

import Pojo.GitDetails;
import Pojo.Repos;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Git {
    private static final String API_URL = Utils.Config.appConfig.get("gitApi");
    private static final String REPO_PER_PAGE = Utils.Config.appConfig.get("reposPerPage");
    private static final String GITHUB_TOKEN = Utils.Config.appConfig.get("GITHUB_TOKEN");

    public static GitDetails getGitDetails(String userName) {
        if(GITHUB_TOKEN == null) System.err.println("WARNING: Please consider adding GITHUB TOKEN to void rate limit, default 60 request/per hour");

        GitDetails gitResponses = getUserDetails(userName).getBody().as(GitDetails.class);
        Response reposResponses = getReposDetails(userName);
        List<Repos> repoPojo = reposResponses.jsonPath().getList("$", Repos.class);
        for (Repos repo : repoPojo) {
            repo.setReleases(getReleaseCount(userName, repo.getName()));
        }
        gitResponses.setRepos(repoPojo);
        return gitResponses;
    }

    private static Response getResponse(String url) {
        RequestSpecification requestSpec = given();
        requestSpec.header("Accept", "application/vnd.github.v3+json");
        if (GITHUB_TOKEN != null) requestSpec.header("authorization", "Bearer " + GITHUB_TOKEN);
        Response response = requestSpec.get(url);
        checkRateLimit(response);
        return response;
    }

    private static Response getUserDetails(String username){
        return getResponse(API_URL + "/users/" + username);
    }

    private static Response getReposDetails(String username) {
        return getResponse(API_URL + "/users/" + username + "/repos?per_page=" + REPO_PER_PAGE);
    }

    private static int getReleaseCount(String username, String repoName) {
        return getResponse(API_URL + "/repos/" + username +"/"+repoName+"/releases").jsonPath().getList("$").size();
    }

    private static void checkRateLimit(Response response){
        if(response.getStatusCode() != 200) {
            System.err.println(response.jsonPath().getString("message"));
            System.exit(1);
        }
    }
}
