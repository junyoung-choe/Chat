package com.example.back.util.jwt.repository;

import com.example.back.util.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<RefreshToken, Long> {

    @Modifying
    @Query("update RefreshToken set token = :newToken where member.id = :memberId")
    int updateRefreshTokenFindByMember(@Param("memberId") Long memberId, @Param("newToken") String newToken);


    @Modifying
    @Query("update RefreshToken set token = :newToken where token = :oldToken")
    int updateRefreshTokenFindByToken(@Param("oldToken") String oldToken, @Param("newToken") String newToken);
}
