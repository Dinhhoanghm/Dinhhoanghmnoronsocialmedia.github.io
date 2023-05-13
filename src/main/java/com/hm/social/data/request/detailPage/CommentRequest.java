package com.hm.social.data.request.detailPage;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class CommentRequest {


    private String commentDesc;

    private int userId;

    private int postId;



//    private UserRequest userRequest;
}
