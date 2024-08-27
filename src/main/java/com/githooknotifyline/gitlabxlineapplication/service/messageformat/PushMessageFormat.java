package com.githooknotifyline.gitlabxlineapplication.service.messageformat;

import com.githooknotifyline.gitlabxlineapplication.dto.CommitDto;
import com.githooknotifyline.gitlabxlineapplication.dto.GitLabEventDto;

import java.util.List;
import java.util.stream.Collectors;

public class PushMessageFormat extends MessageFormatTemplate {


    /**
     * branch 출력, url 정의에 대한 포맷이 달라 재정의
     */
    @Override
    public StringBuilder messageHeader(GitLabEventDto data) {
        StringBuilder header = new StringBuilder();
        header.append(String.format("\n📣%s📣\n", data.getEventName().toUpperCase()));
        header.append(String.format("🧑🏻‍🦱%s\n\n", data.getUserName(), data.getEventName()));
        header.append(String.format("🌐 %s\n\n", getBranchUrl(data.getProject().getWebUrl(), data.getRef())));
        header.append("💡Branch: ");
        header.append(getBranch(data.getRef()) + "\n\n");

        return header;
    }

    @Override
    public String messageBody(GitLabEventDto data) {
        StringBuilder body = new StringBuilder();

        body.append("✅Commits(총 ");
        body.append(data.getCommits().size());
        body.append("개) [\n");
        body.append(getCommits(data.getCommits()));
        body.append("]");


        return body.toString();
    }

    /**
     * Push Message Format
     * Commits [
     * User: who
     * WebUrl: 수정에 대한 url
     * 변경 파일 정보들 (A -> Add, M -> Modified, D -> Removed)
     */
    private String getCommits(List<CommitDto> commitList) {
        if (commitList == null || commitList.isEmpty()) {
            throw new RuntimeException("-Empty-");
//            return "-Empty-\n";
        }

        String commits = commitList.stream().parallel()
                .map(CommitDto -> "👤User: " + CommitDto.getAuthor().getName() + "\n"
                        + "🏷️Title: " + CommitDto.getTitle() + "\n"
                        + CommitDto.getAdded().stream().map(String -> "(A)" + String).collect(Collectors.joining("\n"))
                        + CommitDto.getModified().stream().map(String -> "(M)" + String).collect(Collectors.joining("\n"))
                        + CommitDto.getRemoved().stream().map(String -> "(D)" + String).collect(Collectors.joining("\n"))
                        + "\n").collect(Collectors.joining("\n"));

        return commits;
    }

    private String getBranch(String ref) {
        int parsePos = ref.indexOf('/', ref.indexOf('/') + 1);
        return ref.substring(parsePos);
    }

    private String getBranchUrl(String url, String ref) {
        return url+"/-/tree"+getBranch(ref);
    }


}
