package com.ptit.mobie.taskfamily_service.service.Impl;

import com.ptit.mobie.taskfamily_service.dto.request.PersonalTaskRequest;
import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PersonalTaskResponse;
import com.ptit.mobie.taskfamily_service.exception.ResourceNotFoundException;
import com.ptit.mobie.taskfamily_service.model.PersonalTask;
import com.ptit.mobie.taskfamily_service.repository.PersonalTaskRepository;
import com.ptit.mobie.taskfamily_service.service.PersonalTaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonalTaskServiceImpl implements PersonalTaskService {

    private PersonalTaskRepository personalTaskRepository;

    @Override
    public BaseResponse<PersonalTaskResponse> createTask(PersonalTaskRequest request) {

        PersonalTask task = PersonalTask.builder()
                .description(request.getDescription())
                .name(request.getName())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .note(request.getNote())
                .user(request.getUser())
                .build();

        // Lưu Task
        task = personalTaskRepository.save(task);


        PersonalTaskResponse response = mapToPersonTaskResponse(task);

        // Trả về BaseResponse
        return BaseResponse.<PersonalTaskResponse>builder()
                .statusCode(201)
                .message("PersonalTask created successfully")
                .data(response)
                .build();
    }

    @Override
    public BaseResponse<PersonalTaskResponse> updateTask(Integer id, PersonalTaskRequest request) {
        PersonalTask personalTask = personalTaskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found personalTask with id: "+id));

        personalTask.setDescription(request.getDescription());
        personalTask.setNote(request.getNote());
        personalTask.setName(request.getName());
        personalTask.setStartTime(request.getStartTime());
        personalTask.setEndTime(request.getEndTime());
        personalTaskRepository.save(personalTask);

        PersonalTaskResponse personalTaskResponse = mapToPersonTaskResponse(personalTask);

        return BaseResponse.<PersonalTaskResponse>builder()
                .message("Updated personalTask successfully")
                .statusCode(200)
                .data(personalTaskResponse)
                .build();
    }

    @Override
    public BaseResponse<PersonalTaskResponse> getTaskById(Integer id) {
        PersonalTask personalTask = personalTaskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found personalTask with id "+id));

        PersonalTaskResponse personalTaskResponse = mapToPersonTaskResponse(personalTask);

        return BaseResponse.<PersonalTaskResponse>builder()
                .message("Get personalTask with id "+id+" successfully")
                .statusCode(200)
                .data(personalTaskResponse)
                .build();
    }

    @Override
    public BaseResponse<Void> deleteTask(Integer id) {
        if(!personalTaskRepository.existsById(id)){
            throw new ResourceNotFoundException("Not found personalTask with id: "+id);
        }
        personalTaskRepository.deleteById(id);
        return BaseResponse.<Void>builder()
                .statusCode(200)
                .message("PersonalTask deleted successfully")
                .build();
    }

    @Override
    public PagedBaseResponse<Object> getAllTasks(Pageable pageable) {
        Page<PersonalTask> personalTaskPage = personalTaskRepository.findAll(pageable);

        List<PersonalTaskResponse> personalTaskResponses = personalTaskPage.getContent().stream()
                .map(this::mapToPersonTaskResponse)
                .toList();

        return PagedBaseResponse.builder()
                .statusCode(200)
                .message("PersonalTask retrieved successfully")
                .data(personalTaskResponses)
                .pageNumber(personalTaskPage.getNumber())
                .pageSize(personalTaskPage.getSize())
                .totalElements(personalTaskPage.getTotalElements())
                .totalPages(personalTaskPage.getTotalPages())
                .build();
    }

    private PersonalTaskResponse mapToPersonTaskResponse(PersonalTask task) {
        return PersonalTaskResponse.builder()
                .id(task.getId())
                .description(task.getDescription())
                .name(task.getName())
                .note(task.getNote())
                .startTime(task.getStartTime())
                .endTime(task.getEndTime())
                .user(task.getUser())
                .build();
    }
}
