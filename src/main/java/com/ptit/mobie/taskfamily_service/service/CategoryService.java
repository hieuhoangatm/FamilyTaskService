package com.ptit.mobie.taskfamily_service.service;

import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.CategoryResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.model.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    BaseResponse<Object> createTask(Category category);
    BaseResponse<Category> updateTask(Integer id, Category category);
    BaseResponse<Category> getTaskById(Integer id);
    BaseResponse<Void> deleteTask(Integer id);
    PagedBaseResponse<List<CategoryResponse>> getAllTasks(Pageable pageable);
}
