package com.ptit.mobie.taskfamily_service.service.Impl;

import com.ptit.mobie.taskfamily_service.dto.response.*;
import com.ptit.mobie.taskfamily_service.exception.ResourceNotFoundException;
import com.ptit.mobie.taskfamily_service.model.Category;
import com.ptit.mobie.taskfamily_service.model.Task;
import com.ptit.mobie.taskfamily_service.model.User;
import com.ptit.mobie.taskfamily_service.repository.CategoryRepository;
import com.ptit.mobie.taskfamily_service.repository.UserRepository;
import com.ptit.mobie.taskfamily_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    @Override
    public BaseResponse<Object> createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return BaseResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(savedCategory)
                .message("Created category successfully")
                .build();
    }

    @Override
    public BaseResponse<Category> updateCategory(Integer id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw  new ResourceNotFoundException("Category not found with id: " + id);
        }
        Category existingCategory = optionalCategory.get();
        existingCategory.setName(category.getName());
        // Nếu bạn muốn update thêm tasks thì add ở đây
        categoryRepository.save(existingCategory);
        return BaseResponse.<Category>builder()
                .statusCode(200)
                .data(category)
                .message("Updated category successfully")
                .build();
    }

    @Override
    public BaseResponse<CategoryResponse> getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
//        return BaseResponse.<Category>builder()
//                .message("Get category with id "+id +"successfully")
//                .statusCode(200)
//                .data()
//                .build();
        return BaseResponse.<CategoryResponse>builder()
                .message("Get category with id "+id +"successfully")
                .statusCode(200)
                .data(mapToCategoryResponse(optionalCategory.get()))
                .build();
    }

    @Override
    public BaseResponse<Void> deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
        return BaseResponse.<Void>builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("Deleted category successfully")
                .build();
    }

    @Override
    public PagedBaseResponse<List<CategoryResponse>> getAllCategory(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryResponse> categoryResponses = categoryPage.getContent().stream()
                .map(this::mapToCategoryResponse)
                .toList();


        return PagedBaseResponse.<List<CategoryResponse>>builder()
                .statusCode(200)
                .message("Categories retrieved successfully")
                .data(categoryResponses)
                .pageNumber(categoryPage.getNumber())
                .pageSize(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .build();
    }

    @Override
    public BaseResponse<CategoryResponse> getCategoryWithTask(Integer categoryId, Integer taskId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + categoryId);
        }
        Category category = optionalCategory.get();
        CategoryResponse categoryResponse = mapToCategoryResponse(category);

        // Nếu taskId được cung cấp, lọc danh sách tasks để chỉ chứa task có taskId
        if (taskId != null) {
            List<TaskInCategoryResponse> filteredTasks = categoryResponse.getTasks().stream()
                    .filter(task -> task.getId().equals(taskId))
                    .collect(Collectors.toList());
            if (filteredTasks.isEmpty()) {
                throw new ResourceNotFoundException("Task with id " + taskId + " not found in category " + categoryId);
            }
            categoryResponse.setTasks(filteredTasks);
        }

        return BaseResponse.<CategoryResponse>builder()
                .message("Get category with id " + categoryId + " successfully")
                .statusCode(200)
                .data(categoryResponse)
                .build();
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        List<TaskInCategoryResponse> taskResponses = category.getTasks() == null ? List.of() :
                category.getTasks().stream()
                        .map(this::mapToTaskInCategoryResponse)
                        .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setTasks(taskResponses);

        return categoryResponse;
    }

    private TaskInCategoryResponse mapToTaskInCategoryResponse(Task task) {
        List<UserResponseDTO> userResponseDTOList = task.getUsers()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();

        TaskInCategoryResponse response = new TaskInCategoryResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setIcon(task.getIcon());
        response.setStatus(task.getStatus());
        response.setUsers(userResponseDTOList);
        response.setPoint(task.getPoint());
        response.setDeadline(task.getDeadline());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        response.setCreatedBy(task.getCreatedBy());
        response.setUpdatedBy(task.getUpdatedBy());
        return response;
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
