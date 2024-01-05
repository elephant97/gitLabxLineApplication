package com.githooknotifyline.gitlabxlineapplication.service;

import com.githooknotifyline.gitlabxlineapplication.controller.GitLabEventController;
import com.githooknotifyline.gitlabxlineapplication.model.GitLabEvent;
import com.githooknotifyline.gitlabxlineapplication.model.MessageFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {

    private final URI lineUrl = URI.create("https://notify-api.line.me/api/notify");
    private final String contentType = "application/x-www-form-urlencoded";



    @Override
    public ResponseEntity<String> pushNotify(String lineToken, GitLabEvent content) {
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),content.getAfter());
        MessageFormat messageFormat = new MessageFormat();
        String data = String.format("message=%s", messageFormat.pushFormat(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> pushTagNotify(String lineToken, GitLabEvent content) {
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),content.getAfter());
        MessageFormat messageFormat = new MessageFormat();
        String data = String.format("message=%s", messageFormat.tagFormat(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> issueNotify(String lineToken, GitLabEvent content) {
        MessageFormat messageFormat = new MessageFormat();
        String data = String.format("message=%s", messageFormat.issueFormat(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> mergeRequestNotify(String lineToken, GitLabEvent content) {
        MessageFormat messageFormat = new MessageFormat();
        String data = String.format("message=%s", messageFormat.mergeFormat(content));
        return this.httpPost(lineToken, data);
    }

    public ResponseEntity<String> httpPost(String lineToken, String data) {
        String bearerAuth = "Bearer " + lineToken;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", contentType);
        headers.add("Content-Length", "" + data.getBytes().length);
        headers.add("Authorization", bearerAuth);
        log.info("{} message={}",getClass().getSimpleName(),data);
        RequestEntity request = new RequestEntity(
                data, headers, HttpMethod.POST, lineUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(request, String.class);
        return responseEntity;
    }

}
