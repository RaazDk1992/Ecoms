package com.RaazDk.eComs.security.services;

import com.RaazDk.eComs.models.EcomUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImplementation  implements UserDetails {

    private  static final  Long SerialVersionUID = 1L;
    private Long id;
    private String userName;
    @JsonIgnore
    private  String password;
    private String email;
    private  Collection<? extends GrantedAuthority> grantedAuthorities;


    public  UserDetailsImplementation(Long id, String userName, String password, String email,  Collection<? extends GrantedAuthority> authorities){

        this.email = email;
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.grantedAuthorities =authorities;
    }


    public static UserDetailsImplementation build(EcomUser user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        return  new UserDetailsImplementation(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getAddress(),
                List.of(authority)
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
