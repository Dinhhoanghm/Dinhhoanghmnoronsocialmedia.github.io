package com.hm.social.data.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Topic {

    private int topicId;


    private String topicDesc;


    private int postId;

    public Topic (String topicDesc){
        this.topicDesc = topicDesc;
    }
}
