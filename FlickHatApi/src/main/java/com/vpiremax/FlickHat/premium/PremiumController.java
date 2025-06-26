package com.vpiremax.FlickHat.premium;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.vpiremax.FlickHat.premium.dto.PaymentRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PremiumController {
    @PostMapping("/create-checkout-session")
    public ResponseEntity<?> createCheckoutSession(@RequestBody PaymentRequestDTO request) throws StripeException {
        List<SessionCreateParams.LineItem> lineItems = List.of(
                SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount(999L) // 9.99 USD
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName("Premium Subscription")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5050ap/v1/payment-success")
                .setCancelUrl("https://localhost:5050api/v1/payment-cancel")
                .addAllLineItem(lineItems)
                .build();

        Session session = Session.create(params);

        return ResponseEntity.ok(Map.of("url", session.getUrl()));
    }
}
