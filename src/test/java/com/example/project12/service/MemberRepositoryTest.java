package com.example.project12.service;

import com.example.project12.entity.Member;
import com.example.project12.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    public void 회원가입() {

        Member member = Member.builder()
                .id("admin")
                .name("둘리")
                .password("1234")
                .role("ROLE_ADMIN")
                .build();

        repository.save(member);

    }

    @Test
    public void 목록조회() {

        List<Member> list = repository.findAll();

        for(Member member : list) {
            System.out.println(member);
        }

    }

}
