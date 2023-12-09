package com.eigenai.orchestrator.repository;


import com.eigenai.orchestrator.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Account repository.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
