package com.ptit.mobie.taskfamily_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTaskReponse {
    private Integer id;
    private String username;
    private String nickName;

    private String icon;

}
