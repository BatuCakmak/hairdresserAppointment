package com.erciyes.model;

import com.erciyes.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {


    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Date birthOfDate;
    private String tckn;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }



}
