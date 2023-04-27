package com.hm.social.data.request;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostRequest {
    private int id;
    private String title;
    private String desc;
    private String img;
    private int userid;
}
