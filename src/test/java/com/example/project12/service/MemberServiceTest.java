package com.example.project12.service;

import com.example.project12.dto.MemberDTO;
import com.example.project12.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService service;

    @Test
    public void 목록조회(){

        List<MemberDTO> list = service.getList();

        for(MemberDTO dto : list) {
            System.out.println(dto);
        }

    }
    
    @Test
    public void 상세조회() {
        
        // 조건: 아이디
        MemberDTO dto = service.read("user");

        System.out.println(dto);
        
    }

    @Test
    public void 회원가입() {

        MemberDTO dto = MemberDTO.builder()
                .id("aa")
                .password("1234")
                .name("고길동")
                .role("ROLE_USER")
                .build();

        service.register(dto);

    }

}
