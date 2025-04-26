package com.ptit.mobie.taskfamily_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User implements UserDetails, Serializable {
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

    private String nickName;

    @OneToMany(mappedBy = "user")
    private List<Chore> chores;

    @ManyToMany(mappedBy = "users")  // Đối chiếu với 'users' trong Task
    private List<Task> tasks;

    @OneToMany(mappedBy = "user")
    private List<PersonalTask> personalTasks;

    @OneToMany(mappedBy = "user")
    private List<Reward> rewards;

    private Boolean isActive;


    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
