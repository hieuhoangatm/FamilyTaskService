package com.ptit.mobie.taskfamily_service.controller;

import com.ptit.mobie.taskfamily_service.dto.response.BaseResponse;
import com.ptit.mobie.taskfamily_service.dto.response.CategoryResponse;
import com.ptit.mobie.taskfamily_service.dto.response.PagedBaseResponse;
import com.ptit.mobie.taskfamily_service.model.Category;
import com.ptit.mobie.taskfamily_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<BaseResponse<Object>> createCategory(@RequestBody Category category) {
        BaseResponse<Object> response = categoryService.createTask(category);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Category>> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        BaseResponse<Category> response = categoryService.updateTask(id, category);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Category>> getCategoryById(@PathVariable Integer id) {
        BaseResponse<Category> response = categoryService.getTaskById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCategory(@PathVariable Integer id) {
        BaseResponse<Void> response = categoryService.deleteTask(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<PagedBaseResponse<List<CategoryResponse>>> getAllCategories(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        PagedBaseResponse<List<CategoryResponse>> response = categoryService.getAllTasks(pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
