package com.ptit.mobie.taskfamily_service.model;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "task")
    private List<Chore> chores;



}
