package com.example.project12.controller;

import com.example.project12.dto.BoardDTO;
import com.example.project12.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 이전에는 @Controller를 씀
// API 서버를 제작할 때 사용
@RestController // @Controller + @ResponseBody(데이터 송/수신) 조합
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    @PostMapping("/register")                   //json데이터를 -> dto로
    public ResponseEntity<Integer> register(@RequestBody BoardDTO dto){

        // 새로운 게시물 추가
        int no = service.register(dto);

        // 응답메세지를 생성하여 반환 (201코드와 새로운게시물번호)
        // 이 자체가 응답메세지를 만드는것
        return new ResponseEntity<>(no, HttpStatus.CREATED);
    }

    // 응답의 형태
    // HTML (JSP, Thymeleaf)
    // Text (String, int 등)
    // JSON데이터 (클래스, 리스트, 배열 등)
    // http 응답 메세지 (ResponseEntity) -> 헤더와 바디 직접 정의

//    @ResponseBody // 컨버터를 사용하여 데이터를 변환한 후 전송!
//    public String method1() {
//        return "안녕"; // 텍스트 그대로 반환
//    }
//
//    @ResponseBody
//    public BoardDTO method2() {
//        return new BoardDTO(); // dto -> json문자열로 변환
//    }

    // 매개변수: URL 파라미터 또는 바디데이터 => 작업에 필요한 데이터
    // 반환값: 사용자가 받을 결과
    @GetMapping("/list")
    // ResponseEntity의 타입변수는 바디데이터의 타입
    public ResponseEntity<List<BoardDTO>> getList(){

        List<BoardDTO> list = service.getList();

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    // 매개변수: 작업에 필요한 데이터
    // 단건조회는 PK가 필요함 -> 파라미터 선언
    // 파라미터 형식
    // /board/read?no=1 <- RequestParam(?K=V) 또는 /board/read/1 <- PathValueable(/{변수}
    @GetMapping("/read")
    public ResponseEntity<BoardDTO> read(@RequestParam(name = "no") int no){

        BoardDTO dto = service.read(no);

        return new ResponseEntity<>(dto, HttpStatus.OK);
                                    // dto 클래스구조를 -> json 문자열로 변환
    }
    
    // 작업에 필요한 데이터: 수정할 게시물 데이터 (복잡).
    // 데이터가 (단순) -> URL 파라미터
    // 데이터가 (복잡) -> 메세지 바디
    @PutMapping("/modify")
    public ResponseEntity modify(@RequestBody BoardDTO dto){

        service.modify(dto);

        // 처리결과? 와 응답코드
        // 204 응답코드(요청 성공은 했으나, 반환할 데이터가 없을때 사용), 200을 써도 상관없음
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // NO_CONTENT > 응답코드만 나옴

    }

    // 단건삭제 -> 파라미터 필요
    // 예시: localhost:8080/board/remove?no=1
    @DeleteMapping("/remove")
    public ResponseEntity remove(@RequestParam(name="no") int no) {

        service.remove(no);

        // 처리결과 없음
        // 응답코드 204 (무슨의미? 작업이 잘 처리되었고 결과가 없다는 뜻)
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    
}
