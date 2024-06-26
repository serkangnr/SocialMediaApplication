package com.serkanguner.entity;

import com.serkanguner.constant.Role;
import com.serkanguner.constant.Status;
import com.serkanguner.utility.lowercase.Lowercase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_auth")
public class Auth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    @Size(min = 3, max = 8)
    String password;
    @Email(message = "@gmail.com")
    String email;
    String activationCode;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    Role role = Role.USER;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    Status status = Status.PENDING;


}
