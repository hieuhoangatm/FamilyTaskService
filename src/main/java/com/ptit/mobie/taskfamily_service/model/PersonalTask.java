package com.ptit.mobie.taskfamily_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_personal_task")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class PersonalTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "tbl_usersid")
    private User user;
}
