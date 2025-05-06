package com.ptit.mobie.taskfamily_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptit.mobie.taskfamily_service.enums.TaskStatus;
import com.ptit.mobie.taskfamily_service.model.Category;
import com.ptit.mobie.taskfamily_service.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskInCategoryResponse {
    private Integer id;

    private String title;

    private String icon;

    private TaskStatus status;

    private List<UserResponseDTO> users;

    private Integer point;

    private LocalDate deadline;

    private Date createdAt;

    private Date updatedAt;

    private String createdBy;

    private String updatedBy;

}
