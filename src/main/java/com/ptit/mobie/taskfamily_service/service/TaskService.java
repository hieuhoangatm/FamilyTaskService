package com.ptit.mobie.taskfamily_service.service;

import com.ptit.mobie.taskfamily_service.dto.request.TaskRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.TaskResponse;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    BaseResponse<TaskResponse> createTask(TaskRequest request);
    BaseResponse<TaskResponse> updateTask(Integer id, TaskRequest request);
    BaseResponse<TaskResponse> getTaskById(Integer id);
    BaseResponse<Void> deleteTask(Integer id);
    PagedBaseResponse<Iterable<TaskResponse>> getAllTasks(Pageable pageable);

    BaseResponse<TaskResponse> markTaskAsCompleted(Integer id);

    BaseResponse<Void> checkAndUpdateOverdueTasks();
}
