package com.example.batch.service;

import com.example.batch.domain.Member;
import com.example.batch.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public List<Member> findAll() {
        List<Member> memberList = memberMapper.findAll();
        return null;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public void save(Member member) {
        memberMapper.save(member);
    }
}
