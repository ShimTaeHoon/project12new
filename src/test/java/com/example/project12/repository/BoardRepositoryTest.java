package com.example.project12.repository;

import com.example.project12.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired // 컨테이너로 부터 빈으로 등록된 Boardrepository를 주입받음
    BoardRepository repository;

    @Test
    public void 빈확인() {
        System.out.println("repository:" + repository);
    }

    @Test
    public void 게시물등록() {

        Board board = Board.builder()
                    .title("안녕")
                    .content("안녕하세요")
                    .writer("둘리")
                    .build();

        // save: insert 또는 update 기능이 실행됨
        repository.save(board);

    }

    @Test
    public void 목록조회() {

        List<Board> list =  repository.findAll();

        for(Board board : list) {
            System.out.println(board);
        }

    }

    @Test
    public void 상세조회() {

        Optional<Board> find = repository.findById(1);

        if(find.isPresent() == true){

            Board board = find.get();
            System.out.println(board);
        }

    }

    @Test
    public void 수정() {

        Optional<Board> op =  repository.findById(1);

        if(op.isPresent() == true){
        Board board = op.get();

        board.setContent("dd");
        board.setTitle("dd");

        repository.save(board); // save() -> update 쿼리 실행
        // 테이블에 no가 없으면 insert
        // 있으면 update

        }
    }

    @Test
    public void 삭제() {

        Optional<Board> op = repository.findById(1);

        if(op.isPresent() == true){
            repository.deleteById(1);
        }

    }

}
