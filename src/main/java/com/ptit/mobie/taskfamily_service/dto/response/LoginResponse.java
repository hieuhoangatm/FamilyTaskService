package com.ptit.mobie.taskfamily_service.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginResponse {
    private String token;
    private String username;
    private String role;
}
