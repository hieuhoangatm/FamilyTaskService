package com.ptit.mobie.taskfamily_service.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptit.mobie.taskfamily_service.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String icon;
    private Integer categoryId;
    private TaskStatus status;

    private List<Integer> userIds; // Danh sách id người dùng

    private Integer point;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate deadline;
}
