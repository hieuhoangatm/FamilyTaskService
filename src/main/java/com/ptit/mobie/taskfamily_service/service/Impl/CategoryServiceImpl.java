package com.ptit.mobie.taskfamily_service.service.Impl;

import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.model.Category;
import com.ptit.mobie.taskfamily_service.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public BaseResponse<Category> createTask(Category category) {
        return null;
    }

    @Override
    public BaseResponse<Category> updateTask(Integer id, Category category) {
        return null;
    }

    @Override
    public BaseResponse<Category> getTaskById(Integer id) {
        return null;
    }

    @Override
    public BaseResponse<Void> deleteTask(Integer id) {
        return null;
    }

    @Override
    public PagedBaseResponse<Iterable<Category>> getAllTasks(Pageable pageable) {
        return null;
    }
}
