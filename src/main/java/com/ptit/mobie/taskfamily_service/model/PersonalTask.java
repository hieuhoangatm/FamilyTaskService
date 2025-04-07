package com.ptit.mobie.taskfamily_service.model;

import jakarta.persistence.*;
import lombok.*;

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

    private Date start_time;
    private Date end_time;

    @Column(name = "note")
    private String note;


    @ManyToOne
    @JoinColumn(name = "tbl_usersid")
    private User user;
}
