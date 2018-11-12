package com.mcb.javajuniortask.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Data
public class Debt {
    @Id
    private UUID id;
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "debt")
    @Cascade(CascadeType.ALL)
    List<Payment> payments = new LinkedList<>();
}
