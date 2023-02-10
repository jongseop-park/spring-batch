package com.example.batch.mapper;

import com.example.batch.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<Member> findAll();

    public Member findById(Long id);

    public void save(Member member);
}
