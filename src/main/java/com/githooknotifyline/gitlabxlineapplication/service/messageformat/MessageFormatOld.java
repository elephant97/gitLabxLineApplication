package com.githooknotifyline.gitlabxlineapplication.service.messageformat;

import com.githooknotifyline.gitlabxlineapplication.dto.CommitDto;
import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Slf4j
public class MessageFormatOld {



    public String issueFormat(GitLabEventDto content) {
        StringBuilder issue = new StringBuilder();
        String projectName = content.getProject() != null ? content.getProject().getName() : "Unknown Project";
        String userName = content.getUserName() != null ? content.getUserName() : "Unknown User";

        issue.append(String.format("\n  Project: %s", projectName));
        issue.append("\n  Event: Issue ");
        issue.append(String.format("\n    User: %s", userName));
        // 추가적으로 필요한 정보는 여기에 추가
        return issue.toString();
    }

    public String tagFormat(GitLabEventDto content) {
        StringBuilder gitEvent = new StringBuilder();
        String projectName = content.getProject() != null ? content.getProject().getName() : "Unknown Project";
        String eventKind = content.getObjectKind() != null ? content.getObjectKind() : "Unknown Event";
        String userName = content.getUserName() != null ? content.getUserName() : "Unknown User";
        String tagReference = content.getRef() != null ? content.getRef().replace("refs/tags/", "") : "Unknown Tag Reference";

        gitEvent.append(String.format("\n  Project: %s", projectName));
        gitEvent.append(String.format("\n  Event: %s", eventKind));
        gitEvent.append(String.format("\n  User: %s", userName));
        gitEvent.append(String.format("\n  Tag Reference: %s", tagReference));
        return gitEvent.toString();
    }
}
