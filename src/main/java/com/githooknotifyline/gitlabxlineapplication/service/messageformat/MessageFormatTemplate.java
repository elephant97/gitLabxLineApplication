package com.githooknotifyline.gitlabxlineapplication.service.messageformat;

import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;

public abstract class MessageFormatTemplate {

    public abstract String messageBody(GitLabEventDto data);

    /**
     * 공통 헤더 작성 부
     * [누가 어떤이벤트를 발생했는가]
     * WebUrl: 해당이벤트가 발생 된 url
     */
    public StringBuilder messageHeader(GitLabEventDto data){
        StringBuilder header = new StringBuilder();
        header.append(String.format("\n[%s *%s]\n",data.getUserUsername(),data.getEventName()));
        header.append(String.format("WebUrl: %s\n\n",data.getProject().getWebUrl()));

        return header;
    }


    public final String getMessage(GitLabEventDto data) {
        StringBuilder fullMessage = new StringBuilder();
        fullMessage.append(messageHeader(data));
        fullMessage.append(messageBody(data));
        return fullMessage.toString();
    }

}
