package com.mcb.javajuniortask.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ClientDTO {
    private UUID id;
    private String name;
    private BigDecimal totalDebt;
}
