package com.githooknotifyline.gitlabxlineapplication.handler;

import com.githooknotifyline.gitlabxlineapplication.handler.Adapter.GitLabEventAdapter;
import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;
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
    public ResponseEntity<?> handleEvent(String lineToken, GitLabEventDto data, NotifyService notifyService) {
        log.debug("Event={}","Merge");
        if(data.getObjectAttribute().getState().equals("closed")){
            throw new RuntimeException("closed state do nothing");
        }
        return notifyService.mergeRequestNotify(lineToken, data);
    }
}
