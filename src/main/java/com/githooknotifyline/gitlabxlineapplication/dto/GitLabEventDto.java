package com.githooknotifyline.gitlabxlineapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GitLabEventDto {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("event_type")
    private String eventType;
    private UserDto user;
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
    private ProjectDto project;
    // for push or merge events
    private List<CommitDto> commits;
    @JsonProperty("total_commits_count")
    private Integer totalCommitsCount;
    private RepositoryDto repository;
    @JsonProperty("object_attributes")
    private ObjectAttributeDto objectAttribute;
    // for not events
    private CommitDto commit;
    private String title;
    // for pipeline events
    private List<BuildDto> builds;
    private List<ReviewerOrAssigneesDto> assignees;
    private List<ReviewerOrAssigneesDto> reviewers;
}
