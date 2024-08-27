package com.githooknotifyline.gitlabxlineapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ObjectAttributeDto {
    @JsonProperty("assignee_id")
    private String assigneeId;
    @JsonProperty("merge_status")
    private String mergeStatus;
    @JsonProperty("source_branch")
    private String sourceBranch;
    @JsonProperty("target_branch")
    private String targetBranch;
    @JsonProperty("noteable_type")
    private String noteableType;
    private String note;
    private String url;
    private String title;
    private String state;
    private String action;
    // for pipeline events
    private String status;
    private String ref;

}
