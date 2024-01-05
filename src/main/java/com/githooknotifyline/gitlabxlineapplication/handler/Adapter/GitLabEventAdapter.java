package com.githooknotifyline.gitlabxlineapplication.handler.Adapter;

import com.githooknotifyline.gitlabxlineapplication.model.GitLabEvent;
import com.githooknotifyline.gitlabxlineapplication.service.NotifyService;
import org.springframework.http.ResponseEntity;

public interface GitLabEventAdapter {
    public String getEventType() ;
    ResponseEntity<?> handleEvent(String lineToken, GitLabEvent data, NotifyService notifyService);
}
