package com.example.project12.service;

import com.example.project12.dto.BoardDTO;
import com.example.project12.entity.Board;
import com.example.project12.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 빈으로 등록
@Service
public class BoardServiceImp implements BoardService {

    @Autowired
    BoardRepository repository;

    @Override
    public int register(BoardDTO dto) {

        // Dto -> Entity 변환
        // 부모 인터페이스에게 물려받은 변환메소드 사용
        Board entity = dtoToEntity(dto);

        // DB에 데이터 추가
        repository.save(entity);

        // 새로운 게시물 번호 반환
        return entity.getNo();

    }

    @Override
    public List<BoardDTO> getList() {

        List<Board> entityList = repository.findAll();

        // 엔티티 리스트 -> DTO 리스트 변환
        // 리스트에서 스트림 생성
        // 스트림의 연산자로 변환 작업
        // map(): 중간연산자. 요소를 하나씩 꺼내서 반복 작업
        // collect(): 최종연산자. 스트림을 다른 자료구조로 변환
        List<BoardDTO> dtoList = entityList.stream()
                    .map(entity -> {
                        return entityToDto(entity);
                    })
                    .collect(Collectors.toList());

        // 변환된 리스트 반환
        return dtoList;

    }

    @Override
    public BoardDTO read(int no) {

        // 단건 조회: 조건 필요(식별자, PK)
        Optional<Board> result = repository.findById(no);

        // 값이 있다면 꺼내서 반환
        if(result.isPresent()){

            // Entity -> DTO
            BoardDTO dto = entityToDto(result.get());

            // 변환한 데이터 반환
            return dto; // 리턴값이 있는데 왜 오류가 나띾?
        }

        return null; // 언제나 리턴이 되도록, 함수의 마지막 부분에 추가

    }

    @Override
    public void modify(BoardDTO dto) {

        // 전달받은 게시물 데이터를 그대로 업데이트 하면 안됨!
        // WHY?
        // 1. 변경되면 안되는 데이터가 변경될 수 있음
        // 2. 코드가 실행되는 사이에 해당게시물이 사라질 수 있음

        // 해당 게시물이 DB에 존재하는지 확인
        Optional<Board> optional = repository.findById(dto.getNo());

        // 게시물이 존재한다면 일부 데이터 업데이트
        if( optional.isPresent()) {

            Board board = optional.get();

            // 이 사이트는 게시물의 제목과 내용만 수정 가능!
            board.setTitle(dto.getTitle());
            board.setContent(dto.getContent());

            repository.save(board);

        }
    
    }

    @Override
    public void remove(int no) {

        // 단건 삭제: 조건 필요 (식별자, PK)
        repository.deleteById(no);

    }
}
