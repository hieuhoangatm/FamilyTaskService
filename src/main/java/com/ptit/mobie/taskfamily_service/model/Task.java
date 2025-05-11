package com.ptit.mobie.taskfamily_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptit.mobie.taskfamily_service.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_tasks")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "icon")
    private String icon;

    @ManyToOne
    @JoinColumn(name = "tbl_categoriesid")
    private Category category;

//    @OneToMany(mappedBy = "task")
//    private List<Chore> chores;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;

    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    
    @Column(name = "point")
    private Integer point;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate deadline;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyy")
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
