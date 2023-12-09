package com.eigenai.orchestrator.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type User.
 */
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class PaymentPlan {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Embedded
    private Amount amount;
}
