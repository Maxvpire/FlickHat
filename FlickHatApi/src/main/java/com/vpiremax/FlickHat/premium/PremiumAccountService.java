package com.vpiremax.FlickHat.premium;

import com.vpiremax.FlickHat.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PremiumAccountService {

    private PremiumRepository premiumAccountRepository;

    public boolean isUserPremium(String userId) {
        return premiumAccountRepository
                .findByUserId(userId)
                .map(Premium::isActive)
                .orElse(false);
    }

    public void activatePremium(User user, LocalDateTime endDate) {
        Premium premium = new Premium();
        premium.setUser(user);
        premium.setExpiresAt(endDate);
        premium.setActive(true);
        premiumAccountRepository.save(premium);
    }


    public void checkAndDeactivateExpiredPremium(String userId) {
        premiumAccountRepository.findByUserId(userId).ifPresent(account -> {
            if (account.getExpiresAt().isBefore(LocalDateTime.now())) {
                account.setActive(false);
                premiumAccountRepository.save(account);
            }
        });
    }
}
