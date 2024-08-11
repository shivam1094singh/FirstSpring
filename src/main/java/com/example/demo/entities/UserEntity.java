package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min=4,message = "Username must be min of $ characters")
    @Column(name="name",nullable = false,length = 100)
    private String name;
    @NotEmpty
    @Email(message = "Invalid Email")
    @Column(name="email",nullable = false,length = 100)
    private String email;
    @NotEmpty
    @Size(min=3, max=10, message = "password must be min of 3 and max of 10 char")
    @Column(name="password",nullable = false,length = 100)
    private String password;
    @NotEmpty
    @Column(name="about",nullable = false,length = 100)
    private String about;

}
