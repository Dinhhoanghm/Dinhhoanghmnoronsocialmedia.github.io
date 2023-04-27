package com.hm.social.data.response.DetailPage;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReplyResponse {
    private String replyContent;
    private Integer voteCount;
    private UserResponse userResponse;

}
