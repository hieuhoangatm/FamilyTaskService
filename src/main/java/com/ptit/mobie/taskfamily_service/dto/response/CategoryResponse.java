package com.ptit.mobie.taskfamily_service.dto.response;

import com.ptit.mobie.taskfamily_service.model.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;
    private List<TaskInCategoryResponse> tasks;
}
