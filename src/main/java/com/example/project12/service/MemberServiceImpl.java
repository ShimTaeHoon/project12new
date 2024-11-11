package com.example.project12.service;

import com.example.project12.dto.MemberDTO;
import com.example.project12.entity.Member;
import com.example.project12.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // 빈 등록
// MemberServiceImpl 빈은 MemberService 타입으로 취급됨
// 따라서 MemberService를 상속받은 클래스를 2개이상 등록하면 안됨
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository repository;

    @Override
    public List<MemberDTO> getList() {
        
        List<Member> list = repository.findAll();
        
        // 엔티티리스트 -> DTO리스트
        
        // 스트림을 사용하여 반환
        List<MemberDTO> dtolist = list.stream().map(t -> entityToDto(t)).collect(Collectors.toList());

        return dtolist;

    }

    // T: 스트림의 요소
//    R apply(T t) {
//        return entityToDto(t);
//    }

    @Override
    public MemberDTO read(String id) {

        Optional<Member> optional = repository.findById(id);

        if(optional.isPresent()) {
            Member member = optional.get();
            // Entity -> DTO
            MemberDTO dto = entityToDto(member);
            return dto;
        }

        return null;

    }

    @Override
    public boolean register(MemberDTO dto) {
        
        // 등록시 주의사항:
        // PK가 중복되면 안됨!
        
        // NO는 자동으로 발급되어 중복될일이 없었음
        // 하지만 아이디는 사용자가 직접 입력하기 때문에 중복체크를 별도로 해야함

        // 회원아이디가 사용중인지 체크
        String id = dto.getId();

        MemberDTO readDto = read(id);
        
        //
        if(readDto != null) {
            System.out.println("해당 아이디는 사용중입니다");
            return false; // 여기서 회원가입(함수) 종료
        } else {
            // 데이터가 DB에 없는 경우

            // dto -> entity
            Member member = dtoToEntity(dto);
            repository.save(member);
            return true;
        }

    }
}
