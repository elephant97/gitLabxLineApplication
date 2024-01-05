package com.githooknotifyline.gitlabxlineapplication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Slf4j
public class MessageFormat {

    public String mergeFormat(GitLabEvent content) {
        StringBuilder mergeRequest = new StringBuilder();
        String projectName = content.getProject() != null ? content.getProject().getName() : "Unknown Project";
        String eventKind = content.getObjectKind() != null ? content.getObjectKind() : "Unknown Event";
        String userName = content.getUserName() != null ? content.getUserName() : "Unknown User";

        mergeRequest.append(String.format("\n  Project: %s", projectName));
        mergeRequest.append("\n  Event: ").append(eventKind);
        mergeRequest.append(String.format("\n    User: %s", userName));
        // 추가적으로 필요한 정보는 여기에 추가합니다.
        return mergeRequest.toString();
    }

    public String issueFormat(GitLabEvent content) {
        StringBuilder issue = new StringBuilder();
        String projectName = content.getProject() != null ? content.getProject().getName() : "Unknown Project";
        String userName = content.getUserName() != null ? content.getUserName() : "Unknown User";

        issue.append(String.format("\n  Project: %s", projectName));
        issue.append("\n  Event: Issue ");
        issue.append(String.format("\n    User: %s", userName));
        // 추가적으로 필요한 정보는 여기에 추가
        return issue.toString();
    }

    public String pushFormat(GitLabEvent content) {
        StringBuilder gitEvent = new StringBuilder();
        String projectName = content.getProject() != null ? content.getProject().getName() : "Unknown Project";
        String eventKind = content.getObjectKind() != null ? content.getObjectKind() : "Unknown Event";
        String userName = content.getUserName() != null ? content.getUserName() : "Unknown User";

        gitEvent.append(String.format("\n  Before: %s", content.getBefore()));
        gitEvent.append(String.format("\n  Project: %s", projectName));
        gitEvent.append(String.format("\n  Event: %s", eventKind));
        List<Commit> test=new ArrayList<>();
        test.add(content.getCommits().get(0));
        test.add(content.getCommits().get(1));
        test.add(content.getCommits().get(2));
        if (content.getCommits() != null) {
            if (!content.getCommits().isEmpty()) {
                String commitInfo = test.stream()
                        .map(commit -> commit.getId() + " - " + commit.getMessage().replaceAll("\n",""))
                        .collect(Collectors.joining("\n"));
                content.getCommits().stream()
                        .map(commit -> commit.getId() + " - " + commit.getMessage())
                        .forEach(commit->log.info("commits={}",commit));
                gitEvent.append("\n  Commits:");
                gitEvent.append(commitInfo);
            }
        } else {
            gitEvent.append("\n  Commits: None");
        }

        gitEvent.append(String.format("\n  User: %s", userName));
        return gitEvent.toString();
    }

    public String tagFormat(GitLabEvent content) {
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
