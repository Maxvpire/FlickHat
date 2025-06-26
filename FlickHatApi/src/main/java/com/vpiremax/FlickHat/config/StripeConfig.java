package com.vpiremax.FlickHat.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {
    @Value("${application.stripe.api.secret}")
    private String secretKey;

    @PostConstruct
    public void setup() {
        Stripe.apiKey = secretKey;
    }
}
