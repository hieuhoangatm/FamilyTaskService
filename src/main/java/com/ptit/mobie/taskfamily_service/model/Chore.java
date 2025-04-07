package com.ptit.mobie.taskfamily_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tbl_chores")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Chore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "statuschores")
    private Integer statuschores;

    @Column(name = "point")
    private Integer point;

    @Column(name = "repeat_type")
    private String repeatType;

    @Column(name = "repeat_day")
    private String repeatDay;

    @ManyToOne
    @JoinColumn(name = "tbl_usersid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tbl_tasksid")
    private Task task;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void handleBeforeCreate() {
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = new Date();
    }
}
