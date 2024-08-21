package com.example.back.domain.member.service;

import com.example.back.domain.member.entity.Member;
import com.example.back.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member login(Member member) {
        return new Member("d", "a");
    }
}
