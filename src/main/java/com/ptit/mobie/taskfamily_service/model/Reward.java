package com.ptit.mobie.taskfamily_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity_point")
    private Integer quantityPoint;

    @ManyToOne
    @JoinColumn(name = "tbl_usersid")
    private User user;
}
