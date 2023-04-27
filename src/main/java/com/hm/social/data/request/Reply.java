package com.hm.social.data.request;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Reply {
    private Integer id;
    private Integer userid;
    private Integer postid;
    private Integer commentid;
    private String desc;
}
