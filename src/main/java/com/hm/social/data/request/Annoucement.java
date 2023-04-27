package com.hm.social.data.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Annoucement {

    private int annoucementId;


    private String annoucementDesc;


    private int postId;


    private int userId;

    public Annoucement( String annoucementDesc){
        this.annoucementDesc = annoucementDesc;
    }
}
