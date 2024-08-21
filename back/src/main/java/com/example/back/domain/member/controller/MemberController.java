package com.example.back.domain.member.controller;

import com.example.back.domain.member.dto.MemberRequest;
import static com.example.back.util.response.SuccessResponseEntity.getResponseEntity;

import com.example.back.domain.member.entity.Member;
import com.example.back.domain.member.service.MemberService;
import com.example.back.util.response.SuccessCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("members")
@RestController("members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    final private MemberService memberService;
    // createMember, login -> no filter

    // 회원 가입
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberRequest memberRequest) {
        memberService.createMember(new Member(memberRequest.getEmail(), memberRequest.getPassword()));
        return getResponseEntity(SuccessCode.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequest memberRequest) {
        memberService.login(new Member(memberRequest.getEmail(), memberRequest.getPassword()));
        return getResponseEntity(SuccessCode.OK);
    }

    // 회원 조회
    @GetMapping
    public ResponseEntity<?> getMember(HttpServletRequest request) {
        return getResponseEntity(SuccessCode.OK);
    }

    // 로그아웃
    @GetMapping("logout")
    public ResponseEntity<?> logout(HttpRequest request) {
        return getResponseEntity(SuccessCode.OK);
    }
}
