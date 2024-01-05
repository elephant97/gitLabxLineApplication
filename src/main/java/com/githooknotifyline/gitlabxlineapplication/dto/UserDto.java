package com.githooknotifyline.gitlabxlineapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String username;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private String email;


}
