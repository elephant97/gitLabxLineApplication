package com.githooknotifyline.gitlabxlineapplication.handler;

import com.githooknotifyline.gitlabxlineapplication.handler.Adapter.GitLabEventAdapter;
import com.githooknotifyline.gitlabxlineapplication.model.GitLabEvent;
import com.githooknotifyline.gitlabxlineapplication.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MergeEventHandler implements GitLabEventAdapter {

    @Override
    public String getEventType() {
        return "Merge Request Hook";
    }

    @Override
    public ResponseEntity<?> handleEvent(String lineToken, GitLabEvent data, NotifyService notifyService) {
        log.debug("Event={}","Merge");
        return notifyService.mergeRequestNotify(lineToken, data);
    }
}
