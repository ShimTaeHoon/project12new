package com.example.project12.service;

import com.example.project12.dto.BoardDTO;
import com.example.project12.entity.Board;
import com.example.project12.entity.Member;

import java.util.List;

public interface BoardService {

    // 게시물 등록
    int register(BoardDTO dto);

    // 게시물 목록 조회
    List<BoardDTO> getList();

    // 게시물 상세 조회
    BoardDTO read(int no);
    
    // 게시물 수정
    void modify(BoardDTO dto);

    // 게시물 삭제
    void remove(int no);

    // 변환메소드
    // DTO -> Entity
    default Board dtoToEntity(BoardDTO dto) {

        // 엔티티와 DTO의 구조가 달라짐(외래키, String -> Member로 : writer..Board에)
        // 엔티티의 writer: Member클래스
        // DTO의 wirter: String

        // 주의사항! 이때는 모든 데이터를 입력할 필요 없음. pk값만 입력할 것!
        Member member = Member.builder()
                    .id(dto.getWriter())
                    .build();

        Board entity = Board.builder()
                            .no(dto.getNo())
                            .title(dto.getTitle())
                            .content(dto.getContent())
                            .writer(member)
                            .build();
        // 등록일과 수정일은 JPA에서 자동으로 처리하므로 생략

        return entity;

    }

    // entity -> DTO
    default BoardDTO entityToDto(Board entity) {

        // 1. 디폴트 생성자를 사용하여 객체 생성
//        BoardDTO dto1 = new BoardDTO();
//        dto1.setNo(entity.getNo());
//        dto1.setTitle(entity.getTitle());
//        dto1.setContent(entity.getContent());
//        dto1.setWriter(entity.getWriter().getId());
//        dto1.setRegDate(entity.getRegDate());
//        dto1.setModDate(entity.getModDate());
//
//        // 2. All 생성자를 사용하여 객체 생성
//        BoardDTO dto2 = new BoardDTO(entity.getNo(), entity.getTitle(),  entity.getContent(), entity.getWriter().getId(), entity.getRegDate(), entity.getModDate());
//
        // 3. builder 패턴을 사용하여 객체 생성
        
        BoardDTO dto = BoardDTO.builder()
                                .no(entity.getNo())
                                .title(entity.getTitle())
                                .content(entity.getContent())
                                .writer(entity.getWriter().getId())
                                .regDate(entity.getRegDate())
                                .modDate(entity.getModDate())
                                .build();

        return dto;

    }

}
