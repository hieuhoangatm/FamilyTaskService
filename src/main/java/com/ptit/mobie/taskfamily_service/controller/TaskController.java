package com.ptit.mobie.taskfamily_service.controller;

import com.ptit.mobie.taskfamily_service.dto.request.TaskRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.TaskResponse;
import com.ptit.mobie.taskfamily_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<BaseResponse<TaskResponse>> createTask(@RequestBody TaskRequest request) {
        BaseResponse<TaskResponse> response = taskService.createTask(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<TaskResponse>> updateTask(@PathVariable Integer id, @RequestBody TaskRequest request) {
        BaseResponse<TaskResponse> response = taskService.updateTask(id, request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<TaskResponse>> getTaskById(@PathVariable Integer id) {
        BaseResponse<TaskResponse> response = taskService.getTaskById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteTask(@PathVariable Integer id) {
        BaseResponse<Void> response = taskService.deleteTask(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<PagedBaseResponse<Iterable<TaskResponse>>> getAllTasks(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        PagedBaseResponse<Iterable<TaskResponse>> response = taskService.getAllTasks(pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<BaseResponse<TaskResponse>> markTaskAsCompleted(@PathVariable Integer id) {
        BaseResponse<TaskResponse> response = taskService.markTaskAsCompleted(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PostMapping("/check-overdue")
    public ResponseEntity<BaseResponse<Void>> checkAndUpdateOverdueTasks() {
        BaseResponse<Void> response = taskService.checkAndUpdateOverdueTasks();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
