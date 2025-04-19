package com.ptit.mobie.taskfamily_service.controller;

import com.ptit.mobie.taskfamily_service.dto.request.PersonalTaskRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PersonalTaskResponse;
import com.ptit.mobie.taskfamily_service.service.PersonalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personalTask")
public class PersonalTaskController {

    @Autowired
    private PersonalTaskService personalTaskService;

    @PostMapping
    public ResponseEntity<BaseResponse<PersonalTaskResponse>> createTask(@RequestBody PersonalTaskRequest request) {
        BaseResponse<PersonalTaskResponse> response = personalTaskService.createTask(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PersonalTaskResponse>> updateTask(@PathVariable Integer id, @RequestBody PersonalTaskRequest request) {
        BaseResponse<PersonalTaskResponse> response = personalTaskService.updateTask(id, request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PersonalTaskResponse>> getTaskById(@PathVariable Integer id) {
        BaseResponse<PersonalTaskResponse> response = personalTaskService.getTaskById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteTask(@PathVariable Integer id) {
        BaseResponse<Void> response = personalTaskService.deleteTask(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<PagedBaseResponse<Object>> getAllTasks(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        PagedBaseResponse<Object> response = personalTaskService.getAllTasks(pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
