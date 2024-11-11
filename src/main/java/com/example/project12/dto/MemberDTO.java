package com.example.project12.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    String id;

    String password;

    String name;

    String role;

    LocalDateTime regDate;

    LocalDateTime modDate;

}
