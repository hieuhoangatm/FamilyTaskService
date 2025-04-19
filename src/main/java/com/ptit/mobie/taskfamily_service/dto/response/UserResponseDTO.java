package com.ptit.mobie.taskfamily_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private String gender;
    private String phone;
    private String role;
    private Integer totalPoints;
    private String language;
}
