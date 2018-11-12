package com.mcb.javajuniortask.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Payment {
    @Id
    private UUID id;
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "debt_id")
    private Debt debt;
}
