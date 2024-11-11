package com.example.project12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(length = 50)
    String id;

    @Column(length = 200, nullable = false)
    String password;

    @Column(length = 100, nullable = false)
    String name;

    @Column(length = 100, nullable = false)
    String role; // 예시: ROLE_USER ROLE_ADMIN

}