package com.accounting.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

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
