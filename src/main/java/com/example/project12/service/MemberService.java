package com.example.project12.service;

import com.example.project12.dto.MemberDTO;
import com.example.project12.entity.Member;

import java.util.List;

public interface MemberService {

    // 회원 목록 조회
    List<MemberDTO> getList();

    // 회원 단건 조회
    MemberDTO read(String id);

    // 회원 등록
    boolean register(MemberDTO dto);

    // 모델변환 메소드
    // entity -> DTO
    default MemberDTO entityToDto(Member entity){

        MemberDTO dto = MemberDTO.builder()
                        .id(entity.getId())
                        .password(entity.getPassword())
                        .name(entity.getName())
                        .role(entity.getRole())
                        .regDate(entity.getRegDate())
                        .modDate(entity.getModDate())
                        .build();

        return dto;
    }

    default Member dtoToEntity(MemberDTO dto){

        Member member = Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .role(dto.getRole())
                .build();

        // 해당 변환메소드는 insert 또는 update에 사용됨
        // 등록일과 수정일은 자동으로 처리되므로 필요없음!

        return member;

    }

}
