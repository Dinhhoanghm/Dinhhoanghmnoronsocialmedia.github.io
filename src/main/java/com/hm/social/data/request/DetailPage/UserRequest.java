package com.hm.social.data.request.DetailPage;

import lombok. AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class UserRequest {

    private int userId;


    private String userName;



    private String email;

    private String name;


    private String urlPic;

    public UserRequest(String username, String email, String name, String urlPic) {
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.urlPic = urlPic;

    }




}
