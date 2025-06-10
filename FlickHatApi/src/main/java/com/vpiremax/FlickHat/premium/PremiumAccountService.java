package com.vpiremax.FlickHat.premium;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PremiumAccountService {
    private final PremiumRepository premiumAccountRepository;

    public boolean isUserPremium(String userId) {
        return premiumAccountRepository
                .findByUserId(userId)
                .map(Premium::isActive)
                .orElse(false);
    }
}
