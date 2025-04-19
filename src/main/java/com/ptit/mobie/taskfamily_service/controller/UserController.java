package com.ptit.mobie.taskfamily_service.controller;

import com.ptit.mobie.taskfamily_service.dto.request.UserRequestDTO;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.UserResponseDTO;
import com.ptit.mobie.taskfamily_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponseDTO>> getUserById(@PathVariable Integer id) {
        BaseResponse baseResponse = userService.getUserById(id);
        return ResponseEntity.status(baseResponse.getStatusCode()).body(baseResponse);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserResponseDTO>>> getAllUsers(){
        BaseResponse baseResponse = userService.getAllUsers();
        return ResponseEntity.status(baseResponse.getStatusCode()).body(baseResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponseDTO>> updateUser(@PathVariable Integer id, @RequestBody UserRequestDTO userRequestDTO) {
        BaseResponse baseResponse = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.status(baseResponse.getStatusCode()).body(baseResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable Integer id) {
        BaseResponse baseResponse = userService.deleteUser(id);
        return ResponseEntity.status(baseResponse.getStatusCode()).body(baseResponse);
    }
}
