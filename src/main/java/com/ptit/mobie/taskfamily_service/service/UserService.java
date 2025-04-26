package com.ptit.mobie.taskfamily_service.service;

import com.ptit.mobie.taskfamily_service.dto.request.UserRequestDTO;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    BaseResponse<UserResponseDTO> getUserById(Integer id);
    BaseResponse<List<UserResponseDTO>> getAllUsers();
    BaseResponse<UserResponseDTO> updateUser(Integer id, UserRequestDTO userRequestDTO);
    BaseResponse<Object> deleteUser(Integer id);
}
