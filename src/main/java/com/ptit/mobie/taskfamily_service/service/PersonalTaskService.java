package com.ptit.mobie.taskfamily_service.service;

import com.ptit.mobie.taskfamily_service.dto.request.PersonalTaskRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PersonalTaskResponse;
import org.springframework.data.domain.Pageable;

public interface PersonalTaskService {
    BaseResponse<PersonalTaskResponse> createTask(PersonalTaskRequest request);
    BaseResponse<PersonalTaskResponse> updateTask(Integer id, PersonalTaskRequest request);
    BaseResponse<PersonalTaskResponse> getTaskById(Integer id);
    BaseResponse<Void> deleteTask(Integer id);
    PagedBaseResponse<Object> getAllTasks(Pageable pageable);
}
