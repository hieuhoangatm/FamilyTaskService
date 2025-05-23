package com.ptit.mobie.taskfamily_service.service.Impl;

import com.ptit.mobie.taskfamily_service.dto.request.TaskRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.TaskResponse;
import com.ptit.mobie.taskfamily_service.dto.response.UserTaskReponse;
import com.ptit.mobie.taskfamily_service.enums.TaskStatus;
import com.ptit.mobie.taskfamily_service.exception.ResourceNotFoundException;
import com.ptit.mobie.taskfamily_service.model.Category;
import com.ptit.mobie.taskfamily_service.model.Task;
import com.ptit.mobie.taskfamily_service.model.User;
import com.ptit.mobie.taskfamily_service.repository.CategoryRepository;
import com.ptit.mobie.taskfamily_service.repository.TaskRepository;
import com.ptit.mobie.taskfamily_service.repository.UserRepository;
import com.ptit.mobie.taskfamily_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BaseResponse<TaskResponse> createTask(TaskRequest request) {
        // Tìm Category theo ID
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        List<User> users = userRepository.findAllById(request.getUserIds());
        // Tạo Task mới
        Task task = Task.builder()
                .title(request.getTitle())
                .icon(request.getIcon())
                .category(category)
                .point(request.getPoint())
                .status(request.getStatus() != null ? request.getStatus() : TaskStatus.TODO)
                .deadline(request.getDeadline())
                .users(users)
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
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

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

    @Override
    public BaseResponse<TaskResponse> markTaskAsCompleted(Integer id) {
        // Tìm Task theo ID
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Công việc không tồn tại với id: " + id));

        // Cập nhật trạng thái sang DONE
        task.setStatus(TaskStatus.DONE);

        // Lưu Task đã cập nhật
        task = taskRepository.save(task);

        // Tạo TaskResponse
        TaskResponse response = mapToTaskResponse(task);

        // Trả về BaseResponse
        return BaseResponse.<TaskResponse>builder()
                .statusCode(200)
                .message("Công việc đã được đánh dấu hoàn thành")
                .data(response)
                .build();
    }
    @Scheduled(cron = "0 0 0 * * ?") // Chạy lúc 00:00 mỗi ngày
    public void checkAndUpdateOverdueTasksScheduled() {
        checkAndUpdateOverdueTasks();
    }

    @Override
    public BaseResponse<Void> checkAndUpdateOverdueTasks() {
        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Tìm tất cả các công việc có trạng thái TODO và deadline trước ngày hiện tại
        List<Task> overdueTasks = taskRepository.findAllByStatusAndDeadlineBefore(TaskStatus.TODO, today);

        // Cập nhật trạng thái thành OVERDUE
        for (Task task : overdueTasks) {
            task.setStatus(TaskStatus.OVERDUE);
            taskRepository.save(task);
        }

        // Trả về BaseResponse
        return BaseResponse.<Void>builder()
                .statusCode(200)
                .message("Đã cập nhật trạng thái các công việc quá hạn thành công")
                .build();
    }

    // Helper method để ánh xạ Task sang TaskResponse
    private TaskResponse mapToTaskResponse(Task task) {
        List<UserTaskReponse> userResponses = task.getUsers().stream()
                .map(user -> UserTaskReponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .nickName(user.getNickName())
                        .build())
                .toList();

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .icon(task.getIcon())
                .categoryId(task.getCategory().getId())
                .categoryName(task.getCategory().getName()) // Giả sử Category có field name
                .deadline(task.getDeadline())
                .createdBy(task.getCreatedBy())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .updatedBy(task.getUpdatedBy())
                .status(task.getStatus())
                .point(task.getPoint())
                .users(userResponses)
                .build();
    }
}