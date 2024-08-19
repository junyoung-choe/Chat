package com.example.back.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String nickName;
    private String name;
    private String mobile;

    @Builder
    public Member(String email, String name , String nickname, String mobile) {
        this.email = email;
        this.name = name;
        this.nickName = nickname;
        this.mobile = mobile;
    }
}