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
        BaseResponse<Object> response = categoryService.createCategory(category);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Category>> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        BaseResponse<Category> response = categoryService.updateCategory(id, category);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<BaseResponse<CategoryResponse>> getCategoryById(@PathVariable Integer id) {
//        BaseResponse<CategoryResponse> response = categoryService.getCategoryById(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<BaseResponse<CategoryResponse>> getCategoryWithTask(
            @PathVariable Integer categoryId,
            @RequestParam(value = "taskId", required = false) Integer taskId) {
        BaseResponse<CategoryResponse> response = categoryService.getCategoryWithTask(categoryId, taskId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCategory(@PathVariable Integer id) {
        BaseResponse<Void> response = categoryService.deleteCategory(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<PagedBaseResponse<List<CategoryResponse>>> getAllCategories(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        PagedBaseResponse<List<CategoryResponse>> response = categoryService.getAllCategory(pageable);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
