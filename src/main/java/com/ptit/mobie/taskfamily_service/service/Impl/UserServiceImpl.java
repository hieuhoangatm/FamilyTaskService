package com.ptit.mobie.taskfamily_service.service.Impl;

import com.ptit.mobie.taskfamily_service.dto.request.UserRequestDTO;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.UserResponseDTO;
import com.ptit.mobie.taskfamily_service.exception.InvalidDataException;
import com.ptit.mobie.taskfamily_service.exception.ResourceNotFoundException;
import com.ptit.mobie.taskfamily_service.model.User;
import com.ptit.mobie.taskfamily_service.repository.UserRepository;
import com.ptit.mobie.taskfamily_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public BaseResponse<UserResponseDTO> getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        UserResponseDTO userResponseDTO = mapToResponseDTO(user);
        return BaseResponse.<UserResponseDTO>builder()
                .statusCode(200)
                .data(userResponseDTO)
                .message("Get user by id successfully")
                .build();
    }

    @Override
    public BaseResponse<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = userRepository.findAll()
                                                            .stream()
                                                            .map(this::mapToResponseDTO)
                                                            .toList();
        return BaseResponse.<List<UserResponseDTO>>builder()
                .statusCode(200)
                .data(userResponseDTOList)
                .message("Get all user successfully")
                .build();
    }

    @Override
    public BaseResponse<UserResponseDTO> updateUser(Integer id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
        if (!user.getUsername().equals(userRequestDTO.getUsername()) &&
                userRepository.findByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw new InvalidDataException("Username already exists: " + userRequestDTO.getUsername());
        }
        user.setEmail(userRequestDTO.getEmail());
        user.setGender(userRequestDTO.getGender());
        user.setPhone(userRequestDTO.getPhone());
        user.setRole(userRequestDTO.getRole());
        user.setTotal_points(userRequestDTO.getTotalPoints());
        user.setLanguage(userRequestDTO.getLanguage());

        User updatedUser = userRepository.save(user);

        return BaseResponse.<UserResponseDTO>builder()
                .statusCode(201)
                .data(mapToResponseDTO(updatedUser))
                .message("Updated user successfully")
                .build();
    }

    @Override
    public BaseResponse deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("User not found with id: "+id);
        });
        user.setIsActive(false);
        return BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Deleted user successfully")
                .build();
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setGender(user.getGender());
        responseDTO.setPhone(user.getPhone());
        responseDTO.setRole(user.getRole());
        responseDTO.setTotalPoints(user.getTotal_points());
        responseDTO.setLanguage(user.getLanguage());
        return responseDTO;
    }
}
