package com.hm.social.data.response.detailPage;



import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PostResponse {
    private String PostDesc;
    private UserResponse userResponse;
    private List<CommentResponse> commentResponses;
    private List<String> PostRecommend;
    private Integer CommentCount;
    private String Topic;
}
