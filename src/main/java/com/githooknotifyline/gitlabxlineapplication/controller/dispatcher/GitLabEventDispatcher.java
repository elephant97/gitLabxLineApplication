package com.githooknotifyline.gitlabxlineapplication.controller.dispatcher;

import com.githooknotifyline.gitlabxlineapplication.handler.Adapter.GitLabEventAdapter;
import com.githooknotifyline.gitlabxlineapplication.handler.exception.InvalidHeaderException;
import com.githooknotifyline.gitlabxlineapplication.model.GitLabEvent;
import com.githooknotifyline.gitlabxlineapplication.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GitLabEventDispatcher {
    private final Map<String, GitLabEventAdapter> adapters;

    public GitLabEventDispatcher(List<GitLabEventAdapter> adapterList) {
        this.adapters = new HashMap<>();
        for (GitLabEventAdapter adapter : adapterList) {
            this.adapters.put(adapter.getEventType(), adapter);
            log.info("adapter={}",adapter.getEventType());
        }
    }

    public ResponseEntity<?> dispatch(String gitLabEvent, String lineToken, GitLabEvent data, NotifyService notifyService) throws InvalidHeaderException {
        GitLabEventAdapter adapter = adapters.get(gitLabEvent);
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),data.getAfter());
        if (adapter == null) {
            throw new InvalidHeaderException(gitLabEvent);
        }
        return adapter.handleEvent(lineToken, data, notifyService);
    }
}
