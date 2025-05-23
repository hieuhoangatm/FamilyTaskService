package com.ptit.mobie.taskfamily_service.service;

import com.ptit.mobie.taskfamily_service.dto.request.LoginRequest;
import com.ptit.mobie.taskfamily_service.dto.request.RegisterRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.LoginResponse;
import com.ptit.mobie.taskfamily_service.model.User;

public interface AuthenticationService {
    BaseResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
