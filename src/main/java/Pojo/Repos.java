package Pojo;

import com.google.gson.annotations.SerializedName;

public class Repos {

    @SerializedName("name")
    private final String name;
    @SerializedName("stargazers_count")
    private final Integer star;
    private Integer releases;

    public Repos(String name, Integer star) {
        this.name = name;
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public Integer getStar() {
        return star;
    }

    public Integer getReleases() {
        return releases;
    }

    public void setReleases(Integer releases) {
        this.releases = releases;
    }
}
