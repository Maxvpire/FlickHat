package com.vpiremax.FlickHat.premium;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@RequestMapping("webhooks")
public class StripeWebhookController {
    @Value("${application.stripe.api.secret}")
    private String stripeSecret;

    @PostMapping("/stripe")
    public ResponseEntity<String> handleStripeEvent(HttpServletRequest request) throws IOException {
        String payload = new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines().collect(Collectors.joining("\n"));

        String sigHeader = request.getHeader("Stripe-Signature");
        String endpointSecret = "whsec_gPsdKzEKSNDUubPFqgGq045WlhtErd6x";

        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
            String customerEmail = session.getCustomerDetails().getEmail();

            // Activate premium for the user
            // premiumAccountService.activatePremium(customerEmail);

            System.out.println("Payment succeeded for: " + customerEmail);
        }

        return ResponseEntity.ok("");
    }
}
