package com.ptit.mobie.taskfamily_service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private String username;
    private String email;
    private String gender;
    private String phone;
    private String role;
    private Integer totalPoints;
    private String language;
}
