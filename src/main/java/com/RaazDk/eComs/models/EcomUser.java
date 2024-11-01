package com.RaazDk.eComs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
                                            @UniqueConstraint(columnNames = "email")})
public class EcomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    @NotBlank
    @Size(max = 20)
    private String userName;

    @NotBlank
    @Size(max = 100)
    private String email;
    @Size(max = 11)
    private String phoneNumber;
    private String address;
    private String avatar;
    @NotBlank
    @Column(name = "password", length = 400)
    private String password;
    private boolean enabled = true;
    private  boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    @JsonBackReference
    @ToString.Exclude
    private Role role;



    public EcomUser(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o){
        if(this ==o) return true;
        if(!(o instanceof EcomUser)) return  false;
        return id !=null && id.equals(((EcomUser)o).getId());
    }
}
