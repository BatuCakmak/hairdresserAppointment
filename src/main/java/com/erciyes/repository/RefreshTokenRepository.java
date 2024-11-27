package com.erciyes.repository;

import com.erciyes.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    boolean findAllByUserId(Long id);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.user.id = :userId")
    Optional<RefreshToken> findByUserId(@Param("userId") Long userId);
}
