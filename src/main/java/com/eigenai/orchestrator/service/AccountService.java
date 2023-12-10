package com.eigenai.orchestrator.service;

import com.eigenai.orchestrator.domain.Account;
import com.eigenai.orchestrator.domain.Status;
import com.eigenai.orchestrator.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type Account service.
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Create or update account.
     *
     * @param accountInput the account input
     * @return the account
     */
    public Account createOrUpdate(Account accountInput) {
        return accountRepository.save(accountInput);
    }

    /**
     * Find by id account.
     *
     * @param id the id
     * @return the account
     */
    public Account findById(Long id) {
        return accountRepository.findById(id).get();
    }

    /**
     * Delete by id status.
     *
     * @param id the id
     * @return the status
     */
    public Status deleteById(Long id) {
        accountRepository.deleteById(id);
        return SUCCESS;
    }
}
