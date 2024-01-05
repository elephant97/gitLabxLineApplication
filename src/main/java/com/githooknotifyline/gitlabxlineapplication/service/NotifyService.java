package com.githooknotifyline.gitlabxlineapplication.service;

import com.githooknotifyline.gitlabxlineapplication.model.GitLabEvent;
import org.springframework.http.ResponseEntity;

public interface NotifyService {

    ResponseEntity<String> pushNotify(String lineToken, GitLabEvent content);

    ResponseEntity<String> pushTagNotify(String lineToken,GitLabEvent content);

    ResponseEntity<String> issueNotify(String lineToken, GitLabEvent content);

    ResponseEntity<String> mergeRequestNotify(String lineToken, GitLabEvent content);

    ResponseEntity<String> httpPost(String lineToken, String data);
}
