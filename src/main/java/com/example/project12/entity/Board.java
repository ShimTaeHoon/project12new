package com.example.project12.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Board extends BaseEntity {

    // PK + Auto Increament
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no;

    @Column(length = 100, nullable = false)
    String title;

    @Column(length = 1500, nullable = false)
    String content;

//    @Column(length = 50, nullable = false)
    // 외래키 추가 방법
    // 자료형 바꾸기 (String->Member)
//    String writer;
    @ManyToOne //관계차수 1:N
    Member writer;
    
    // 1:1 => 회원은 게시물 하나만 작성할 수 있다
    // 1:N => 회원은 게시물 여러개를 작성할 수 있다

}
