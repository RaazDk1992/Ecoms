package com.RaazDk.eComs.security.services;

import com.RaazDk.eComs.models.EcomUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class EcomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long Id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean is2faEnabled;




    public EcomUserDetails(Long id, String userName,String firstName, String lastName, String email, String password, boolean is2faEnabled, Collection<? extends GrantedAuthority> authorities){

        this.Id = id;
        this.username = userName;
        this.email = email;
        this.firstName =firstName;
        this.lastName = lastName;
        this.password = password;
        this.is2faEnabled = is2faEnabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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


    public static EcomUserDetails build(EcomUser user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        return new EcomUserDetails(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.is2faEnabled(),
                List.of(authority)
        );
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return  true;
        if(o == null || getClass()!= o.getClass()) return  false;
        EcomUserDetails ecomUser = (EcomUserDetails) o;
        return Objects.equals(Id,ecomUser.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }


}
