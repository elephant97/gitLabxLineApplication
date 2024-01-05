package com.githooknotifyline.gitlabxlineapplication.controller;

import com.githooknotifyline.gitlabxlineapplication.controller.dispatcher.GitLabEventDispatcher;
import com.githooknotifyline.gitlabxlineapplication.handler.exception.InvalidHeaderException;
import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;
import com.githooknotifyline.gitlabxlineapplication.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GitLabEventController {
    private final GitLabEventDispatcher dispatcher;
    private final NotifyService notifyService;

    public GitLabEventController(GitLabEventDispatcher dispatcher,NotifyService notifyService) {
        this.notifyService = notifyService;
        this.dispatcher = dispatcher;
    }

    @PostMapping("/")
    public ResponseEntity<?> getNotify(@RequestHeader(value = "X-Gitlab-Token") String lineToken,
                                       @RequestHeader(value = "X-Gitlab-Event") String gitLabEvent,
                                       @RequestBody GitLabEventDto data) throws InvalidHeaderException {
        log.info(" handlerEvent event={}",gitLabEvent);
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),data.getAfter());
        return dispatcher.dispatch(gitLabEvent, lineToken, data, notifyService);
    }
}
