package com.githooknotifyline.gitlabxlineapplication.service;

import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;
import com.githooknotifyline.gitlabxlineapplication.service.messageformat.*;
import lombok.extern.slf4j.Slf4j;
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
    private final String contentType = "application/x-www-form-urlencoded; charset=utf-8";


    @Override
    public ResponseEntity<String> pushNotify(String lineToken, GitLabEventDto content) {
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),content.getAfter());
        MessageFormatTemplate messageFormat = new PushMessageFormat();
        String data = String.format("message=%s", messageFormat.getMessage(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> pushTagNotify(String lineToken, GitLabEventDto content) {
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),content.getAfter());
        MessageFormatOld messageFormatOld = new MessageFormatOld();
        String data = String.format("message=%s", messageFormatOld.tagFormat(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> issueNotify(String lineToken, GitLabEventDto content) {
        MessageFormatOld messageFormatOld = new MessageFormatOld();
        String data = String.format("message=%s", messageFormatOld.issueFormat(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> mergeRequestNotify(String lineToken, GitLabEventDto content) {
        MessageFormatTemplate messageFormat = new MergeMessageFormat();
        String data = String.format("message=%s", messageFormat.getMessage(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> noteCommentNotify(String lineToken, GitLabEventDto content) {
        MessageFormatTemplate messageFormat = new NoteMessageFormat();
        String data = String.format("message=%s", messageFormat.getMessage(content));
        return this.httpPost(lineToken, data);
    }

    @Override
    public ResponseEntity<String> pipelineStatusNotify(String lineToken, GitLabEventDto content) {
        MessageFormatTemplate messageFormat = new PipelineMessageFormat();
        String data = String.format("message=%s", messageFormat.getMessage(content));
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
