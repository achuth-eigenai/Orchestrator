package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.PaymentPlan;
import com.eigenai.orchestrator.vo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type Churn model controller.
 */
@RestController
@RequestMapping("v1/payment")
public class PaymentController {
    /**
     * Create payment payment.
     *
     * @param paymentPlan the payment input
     * @return the payment
     */
    @PostMapping("/")
    public ResponseEntity<PaymentPlan> createOrUpdate(@RequestBody PaymentPlan paymentPlan) {
        return ResponseEntity.ok(paymentPlan);
    }

    /**
     * Gets payment.
     *
     * @param paymentname the paymentname
     * @return the payment
     */
    @GetMapping("/{paymentname}")
    public ResponseEntity<PaymentPlan> getPaymentPlan(@PathVariable String paymentname) {
        return ResponseEntity.ok(new PaymentPlan());
    }

    /**
     * Delete payment status.
     *
     * @param id the id
     * @return the status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePaymentPlan(@PathVariable Long id) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }
}