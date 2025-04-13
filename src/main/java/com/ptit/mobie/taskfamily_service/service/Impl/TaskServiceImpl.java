package com.ptit.mobie.taskfamily_service.service.Impl;

import com.ptit.mobie.taskfamily_service.dto.request.TaskRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.TaskResponse;
import com.ptit.mobie.taskfamily_service.model.Category;
import com.ptit.mobie.taskfamily_service.model.Task;
import com.ptit.mobie.taskfamily_service.repository.CategoryRepository;
import com.ptit.mobie.taskfamily_service.repository.TaskRepository;
import com.ptit.mobie.taskfamily_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BaseResponse<TaskResponse> createTask(TaskRequest request) {
        // Tìm Category theo ID
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        // Tạo Task mới
        Task task = Task.builder()
                .title(request.getTitle())
                .icon(request.getIcon())
                .category(category)
                .build();

        // Lưu Task
        task = taskRepository.save(task);

        // Tạo TaskResponse
        TaskResponse response = mapToTaskResponse(task);

        // Trả về BaseResponse
        return BaseResponse.<TaskResponse>builder()
                .statusCode(201)
                .message("Task created successfully")
                .data(response)
                .build();
    }

    @Override
    public BaseResponse<TaskResponse> updateTask(Integer id, TaskRequest request) {
        // Tìm Task theo ID
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // Tìm Category theo ID
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        // Cập nhật thông tin Task
        task.setTitle(request.getTitle());
        task.setIcon(request.getIcon());
        task.setCategory(category);

        // Lưu Task đã cập nhật
        task = taskRepository.save(task);

        // Tạo TaskResponse
        TaskResponse response = mapToTaskResponse(task);

        // Trả về BaseResponse
        return BaseResponse.<TaskResponse>builder()
                .statusCode(200)
                .message("Task updated successfully")
                .data(response)
                .build();
    }

    @Override
    public BaseResponse<TaskResponse> getTaskById(Integer id) {
        // Tìm Task theo ID
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // Tạo TaskResponse
        TaskResponse response = mapToTaskResponse(task);

        // Trả về BaseResponse
        return BaseResponse.<TaskResponse>builder()
                .statusCode(200)
                .message("Task retrieved successfully")
                .data(response)
                .build();
    }

    @Override
    public BaseResponse<Void> deleteTask(Integer id) {
        // Kiểm tra Task có tồn tại không
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        // Xóa Task
        taskRepository.deleteById(id);

        // Trả về BaseResponse
        return BaseResponse.<Void>builder()
                .statusCode(200)
                .message("Task deleted successfully")
                .build();
    }

    @Override
    public PagedBaseResponse<Iterable<TaskResponse>> getAllTasks(Pageable pageable) {
        // Lấy danh sách Task với phân trang
        Page<Task> taskPage = taskRepository.findAll(pageable);

        // Chuyển đổi sang TaskResponse
        List<TaskResponse> taskResponses = taskPage.getContent().stream()
                .map(this::mapToTaskResponse)
                .collect(Collectors.toList());

        // Trả về PagedBaseResponse với phân trang
        return PagedBaseResponse.<Iterable<TaskResponse>>builder()
                .statusCode(200)
                .message("Tasks retrieved successfully")
                .data(taskResponses)
                .pageNumber(taskPage.getNumber())
                .pageSize(taskPage.getSize())
                .totalElements(taskPage.getTotalElements())
                .totalPages(taskPage.getTotalPages())
                .build();
    }

    // Helper method để ánh xạ Task sang TaskResponse
    private TaskResponse mapToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .icon(task.getIcon())
                .categoryId(task.getCategory().getId())
                .categoryName(task.getCategory().getName()) // Giả sử Category có field name
                .build();
    }
}