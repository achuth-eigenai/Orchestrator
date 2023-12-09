package com.eigenai.orchestrator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * The type User.
 */
public class Amount {
    /**
     * The Currency.
     */
    Currency currency;
    /**
     * The Amount.
     */
    BigDecimal amount;
}
