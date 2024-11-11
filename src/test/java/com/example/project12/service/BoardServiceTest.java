package com.example.project12.service;

import com.example.project12.dto.BoardDTO;
import com.example.project12.entity.Board;
import com.example.project12.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService service;

    @Autowired
    BoardRepository repository;

    @Test
    public void 게시물추가() {
        // 추가할 게시물 데이터 생성
        BoardDTO dto = BoardDTO.builder()
                .title("안녕")
                .content("안녕하세요")
                .writer("둘리")
                .build();

        // 게시물 추가
        int newno = service.register(dto);

        System.out.println("새로운 게시물 번호: " + newno);


    }

    @Test
    public void 목록조회() {

        // 서비스를 사용하여 게시물 목록을 조회하는 코드 작성
        List<BoardDTO> boardList = service.getList();

        for (BoardDTO dto : boardList) {
            System.out.println(dto);
        }

    }

    @Test
    public void 단건조회() {

        // DB에 존재하는 NO를 사용할 것
        BoardDTO dto = service.read(2);
        System.out.println(dto);

    }

    @Test
    public void 수정() {

        // 기존 게시물 조회
        BoardDTO dto = service.read(2);

        // 일부 데이터 변경
        dto.setContent("ㅇㅇ");

        // insert와 달리 update는 no가 꼭 포함되어 있어야함
        service.modify(dto);

    }

    @Test
    public void 단건삭제() {
        // 삭제할 게시물 번호를 넘겨줌
        service.remove(2);
    }

}