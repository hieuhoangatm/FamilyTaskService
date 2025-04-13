package com.ptit.mobie.taskfamily_service.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String icon;
    private Integer categoryId;
}
