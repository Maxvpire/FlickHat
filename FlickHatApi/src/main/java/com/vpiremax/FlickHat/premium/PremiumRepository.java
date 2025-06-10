package com.vpiremax.FlickHat.premium;

import com.vpiremax.FlickHat.user.User;
import com.vpiremax.FlickHat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PremiumRepository extends JpaRepository<Premium, String> {
    Optional<Premium> findByUserId(String userId);

    // Or find by user entity if needed
    Optional<Premium> findByUser(User user);

    // Optional: find all active accounts
    List<Premium> findAllByActiveTrue();
}
