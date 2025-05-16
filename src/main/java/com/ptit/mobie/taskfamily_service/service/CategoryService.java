package com.ptit.mobie.taskfamily_service.service;

import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.CategoryResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.model.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    BaseResponse<Object> createCategory(Category category);
    BaseResponse<Category> updateCategory(Integer id, Category category);
    BaseResponse<CategoryResponse> getCategoryById(Integer id);
    BaseResponse<Void> deleteCategory(Integer id);
    PagedBaseResponse<List<CategoryResponse>> getAllCategory(Pageable pageable);

    BaseResponse<CategoryResponse> getCategoryWithTask(Integer categoryId, Integer taskId);
}
