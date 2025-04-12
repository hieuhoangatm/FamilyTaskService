package com.ptit.mobie.taskfamily_service.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String gender;
    private String phone;
    private String role;
    private String language;
}
