package com.githooknotifyline.gitlabxlineapplication.service.messageformat;

import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;
import com.githooknotifyline.gitlabxlineapplication.dto.ReviewerOrAssigneesDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MergeMessageFormat extends MessageFormatTemplate {

    /**
     * username, url에 대한 포맷이 달라 재정의
     */
    @Override
    public StringBuilder messageHeader(GitLabEventDto data) {
        StringBuilder header = new StringBuilder();
        //Merge에서는 user_username이 아닌 username을 사용해야함
        header.append(String.format("\n📣%s📣\n",data.getEventType().toUpperCase()));
        header.append(String.format("[%s ▶️ %s]\n"
                ,data.getObjectAttribute().getSourceBranch()
                ,data.getObjectAttribute().getTargetBranch()));
        header.append(String.format("🧑🏻‍🦱%s\n",data.getUser().getName()));
        header.append(String.format("✅MR State\n[%s]\n\n", Optional.ofNullable(data.getObjectAttribute().getAction())
                        .map(String::toUpperCase)
                        .orElse("")));
        header.append(String.format("🌐 %s\n\n",data.getObjectAttribute().getUrl()));

        return header;
    }


    /** Merge Request Message Format
     * Reviewers: who, who ..
     * Assignees: who, who ..
     * merge-flow: A -> B
     * message: ..
     */
    @Override
    public String messageBody(GitLabEventDto data) {
        StringBuilder body = new StringBuilder();

        body.append(getReviewers(data.getReviewers())+"\n");
        body.append(getAssignees(data.getAssignees()));

        body.append(String.format("\n📬Message:\n %s",data.getObjectAttribute().getTitle()));

        return body.toString();
    }

    public String getReviewers(List<ReviewerOrAssigneesDto> reviewerList){

        if (reviewerList == null || reviewerList.isEmpty()) {
            return"👁️Reviewers: None";
        }

        String reviewers = reviewerList.stream()
                .map(ReviewerOrAssigneesDto::getName)
                .collect(Collectors.joining(", "));

        return String.format("👁️Reviewers: %s\n",reviewers);
    }

    public String getAssignees(List<ReviewerOrAssigneesDto> AssigneeList){

        if (AssigneeList == null || AssigneeList.isEmpty()) {
            return"🌱Assignees: None";
        }

        String reviewers = AssigneeList.stream()
                .map(ReviewerOrAssigneesDto::getName)
                .collect(Collectors.joining(","));

        return String.format("🌱Assignees: %s\n",reviewers);
    }
}
