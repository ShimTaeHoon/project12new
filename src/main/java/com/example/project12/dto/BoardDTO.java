package com.example.project12.dto;

// DTO: 뷰, Controller에 데이터를 전달하는 역할

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    
    int no;

    String title;

    String content;

    String writer;

    LocalDateTime regDate;

    LocalDateTime modDate;
    
}
