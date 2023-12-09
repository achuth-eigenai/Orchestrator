package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.Account;
import com.eigenai.orchestrator.domain.ChurnModel;
import com.eigenai.orchestrator.domain.PaymentPlan;
import com.eigenai.orchestrator.domain.Account;
import com.eigenai.orchestrator.service.AccountService;
import com.eigenai.orchestrator.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type Churn model controller.
 */
@RestController
@RequestMapping("v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * Create account account.
     *
     * @param accountInput the account input
     * @return the account
     */
    @PostMapping("/")
    public ResponseEntity<Account> createOrUpdateAccount(@RequestBody Account accountInput) {
        return ResponseEntity.ok(accountService.createOrUpdate(accountInput));
    }

    /**
     * Gets account.
     *
     * @param id the id
     * @return the account
     */
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    /**
     * Delete account status.
     *
     * @param id the id
     * @return the status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
    }
}