package com.ptit.mobie.taskfamily_service.dto.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {
    private Integer id;
    private String title;
    private String icon;
    private Integer categoryId;
    private String categoryName;
}
