package com.githooknotifyline.gitlabxlineapplication.handler;

import com.githooknotifyline.gitlabxlineapplication.handler.Adapter.GitLabEventAdapter;
import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;
import com.githooknotifyline.gitlabxlineapplication.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PushEventHandler implements GitLabEventAdapter {
    @Override
    public String getEventType() {
        return "Push Hook";
    }

    @Override
    public ResponseEntity<?> handleEvent(String lineToken, GitLabEventDto data, NotifyService notifyService) {
        log.debug("Event={}","PushTag");
        log.info("##{} - handlerEvent Data={}",getClass().getSimpleName(),data.getAfter());
        return notifyService.pushNotify(lineToken, data);
    }
}
