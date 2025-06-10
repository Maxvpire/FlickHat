package com.vpiremax.FlickHat.premium;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("premiumChecker")
@RequiredArgsConstructor
public class PremiumChecker {

    private PremiumAccountService premiumService;

    public boolean isPremium(String userId) {
        return premiumService.isUserPremium(userId);
    }
}

