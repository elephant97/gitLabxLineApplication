package com.githooknotifyline.gitlabxlineapplication.service;

import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;
import org.springframework.http.ResponseEntity;

public interface NotifyService {

    ResponseEntity<String> pushNotify(String lineToken, GitLabEventDto content);

    ResponseEntity<String> pushTagNotify(String lineToken, GitLabEventDto content);

    ResponseEntity<String> issueNotify(String lineToken, GitLabEventDto content);

    ResponseEntity<String> mergeRequestNotify(String lineToken, GitLabEventDto content);

    ResponseEntity<String> httpPost(String lineToken, String data);
}
