package com.nogayhusrev.accounting.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private Boolean enabled;

    @ManyToOne
    private Role role;


    @ManyToOne
    private Company company;


}
