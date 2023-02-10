package com.example.batch.service;

import com.example.batch.domain.Member;

import java.util.List;

public interface MemberService {

    public List<Member> findAll();

    public Member findById(Long id);

    public void save(Member member);
}
