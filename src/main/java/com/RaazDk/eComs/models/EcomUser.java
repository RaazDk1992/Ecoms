package com.RaazDk.eComs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@Table(name = "eusers", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class EcomUser {

    public EcomUser(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(name = "username")


    @NotBlank
    private String userName;
    @NotBlank
    @Column(name = "email")
    private String email;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired=true;
    private boolean enabled = true;
    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;
    private boolean is2faEnabled = false;
    private String signupMethod;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonBackReference
    @ToString.Exclude
    private Role role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    private  LocalDate updateTime;


    public EcomUser(String email, String userName, String password){
        this.email =email;
        this.userName = userName;
        this.password = password;
    }


    @Override
    public boolean equals(Object o){
        if(this ==o) return true;
        if(!(o instanceof EcomUser)) return  false;
        return userId !=null && userId.equals(((EcomUser)o).getUserId());
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(userId);
    }
}
