package com.hm.social.data.response.detailPage;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CommentResponse {
    private UserResponse userCommentResponse;
    private String commentDesc;
    private Integer voteCount;
    private Integer replyCount;
    private List<ReplyResponse> replyResponses;
}
