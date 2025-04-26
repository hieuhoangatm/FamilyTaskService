package com.ptit.mobie.taskfamily_service.dto.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptit.mobie.taskfamily_service.enums.TaskStatus;
import com.ptit.mobie.taskfamily_service.model.User;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private TaskStatus status; // Enum cho trạng thái

    private List<UserTaskReponse> users;

    private Integer point;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate deadline;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
}
