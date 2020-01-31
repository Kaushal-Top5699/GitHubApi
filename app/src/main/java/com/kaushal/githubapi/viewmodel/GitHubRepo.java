package com.kaushal.githubapi.viewmodel;

import com.google.gson.annotations.SerializedName;

public class GitHubRepo {

    @SerializedName("name")
    private String repository;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    public GitHubRepo(String repository, String description, String language) {
        this.setRepository(repository);
        this.setDescription(description);
        this.setLanguage(language);
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
