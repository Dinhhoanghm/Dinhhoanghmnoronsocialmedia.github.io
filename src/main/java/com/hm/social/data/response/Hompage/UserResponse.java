
package com.hm.social.data.response.Hompage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class UserResponse {

    private String userName;
    private String userAvatar;


}
