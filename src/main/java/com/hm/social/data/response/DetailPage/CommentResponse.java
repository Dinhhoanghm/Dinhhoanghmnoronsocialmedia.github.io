package com.hm.social.data.response.DetailPage;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class CommentResponse {
    private UserResponse userResponse;
    private String commentDesc;
    private Integer voteCount;
    private Integer replyCount;
    private List<ReplyResponse> replyResponses;
}
