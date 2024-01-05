package com.githooknotifyline.gitlabxlineapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GitLabEvent {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_name")
    private String eventName;
    private String before;
    private String after;
    private String ref;
    @JsonProperty("ref_protected")
    private Boolean refProtected;
    @JsonProperty("checkout_sha")
    private String checkoutSha;
    private String message;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_username")
    private String userUsername;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("user_avatar")
    private String userAvatar;
    @JsonProperty("project_id")
    private Integer projectId;
    private Project project;
    private List<Commit> commits;
    @JsonProperty("total_commits_count")
    private Integer totalCommitsCount;
    private Repository repository;
}

@Data
 class Commit {
    private String id;
    private String message;
    private String title;
    private String timestamp;
    private String url;
    private Author author;
    private List<String> added;
    private List<String> modified;
    private List<String> removed;
}

@Data
 class Author {
    private String name;
    private String email;
}

@Data
 class Repository {
    private String name;
    private String url;
    private String description;
    private String homepage;
    @JsonProperty("git_http_url")
    private String gitHttpUrl;
    @JsonProperty("git_ssh_url")
    private String gitSshUrl;
    @JsonProperty("visibility_level")
    private Integer visibilityLevel;
}

@Data
class Project {
    private Integer id;
    private String name;
    private String description;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("git_ssh_url")
    private String gitSshUrl;
    @JsonProperty("git_http_url")
    private String gitHttpUrl;
    private String namespace;
    @JsonProperty("visibility_level")
    private Integer visibilityLevel;
    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;
    @JsonProperty("default_branch")
    private String defaultBranch;
    private String homepage;
    private String url;
    @JsonProperty("ssh_url")
    private String sshUrl;
    @JsonProperty("http_url")
    private String httpUrl;
}
