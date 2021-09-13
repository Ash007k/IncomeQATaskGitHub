package Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitDetails {
    @SerializedName("login")
    private final String username;
    @SerializedName("name")
    private final String name;
    @SerializedName("created_at")
    private final String createdOn;

    private List<Repos> repos = null;

    public GitDetails(String username, String name, String createdOn) {
        this.username = username;
        this.name = name;
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        String userDetails = "Username = " + username +
                "\nName = " + name +
                "\nCreated on = " + createdOn.split("T")[0];
        StringBuilder repoDetails = new StringBuilder();
        if(repos.size() > 0) {
            for (int index = 0; index < repos.size(); index++) {
                Repos repoList = repos.get(index);
                repoDetails.append("\n\t Repository ").append(index + 1).append(" = ").append(repoList.getName()).append("\n\t\tStars = ").append(repoList.getStar()).append("\n\t\tReleases = ").append(repoList.getReleases());
            }
        } else {
            repoDetails = new StringBuilder("\n\tNo Repository Available");
        }
        return userDetails + repoDetails;
    }

    public void setRepos(List<Repos> repos) {
        this.repos = repos;
    }

    public List<Repos> getRepos() { return repos; }
}
