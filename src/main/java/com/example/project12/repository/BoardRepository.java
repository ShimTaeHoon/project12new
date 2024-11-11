package com.example.project12.repository;

import com.example.project12.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// DB에 접근하여 조회, 조작하는 역할
// 제네릭: 실제로 사용할 데이터의 자료형을 생성시 지정하는 기능
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 제네릭 타입으로 지정된 엔티티를 사용하여 CRUD 기능이 추가됨
    // 인터페이스를 상속받은 구현클래스가 만들어짐
    // 구현클래스가 인스턴스로 생성됨
    // 인스턴스가 스프링 컨테이너에 빈으로 등록됨


}
