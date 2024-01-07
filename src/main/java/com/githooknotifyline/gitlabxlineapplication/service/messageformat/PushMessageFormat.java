package com.githooknotifyline.gitlabxlineapplication.service.messageformat;

import com.githooknotifyline.gitlabxlineapplication.dto.CommitDto;
import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;

import java.util.List;
import java.util.stream.Collectors;

public class PushMessageFormat extends MessageFormatTemplate {

    @Override
    public String messageBody(GitLabEventDto data) {
        StringBuilder body = new StringBuilder();
        body.append("* Commits [\n");
        body.append(getCommits(data.getCommits()));
        body.append("]");


        return body.toString();
    }

    /** Push Message Format
     * Commits [
     * User: who
     * WebUrl: 수정에 대한 url
     * 변경 파일 정보들 (A -> Add, M -> Modified, D -> Removed)
     */
    private String getCommits(List<CommitDto> commitList) {
        if(commitList == null || commitList.isEmpty()){
            return "-Empty-\n";
        }

        String commits= commitList.stream().parallel()
                .map(CommitDto -> "* User: " + CommitDto.getAuthor().getName() + "\n"
                + "WebUrl: " + CommitDto.getUrl() + "\n"
                + CommitDto.getAdded().stream().map(String -> "(A)"+String).collect(Collectors.joining())
                + CommitDto.getModified().stream().map(String -> "(M)"+String).collect(Collectors.joining())
                + CommitDto.getRemoved().stream().map(String -> "(D)"+String).collect(Collectors.joining())
                +"\n").collect(Collectors.joining("\n"));

        return commits;
    }
}
