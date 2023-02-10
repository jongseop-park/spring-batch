package com.example.batch.service;

import com.example.batch.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    @Rollback(value = true)
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setName("memberA");
        member.setAge(30);

        // when
        memberService.save(member);

        // then
    }
}
