package com.ptit.mobie.taskfamily_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    private String role;

    @Column(name = "total_points")
    private Integer total_points;

    @Column(name = "language")
    private String language;

    @OneToMany(mappedBy = "user")
    private List<Chore> chores;

    @OneToMany(mappedBy = "user")
    private List<PersonalTask> personalTasks;

    @OneToMany(mappedBy = "user")
    private List<Reward> rewards;
}
