package com.githooknotifyline.gitlabxlineapplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommitDto {
    private String id;
    private String message;
    private String title;
    private String timestamp;
    private String url;
    private AuthorDto author;
    private List<String> added;
    private List<String> modified;
    private List<String> removed;
}
