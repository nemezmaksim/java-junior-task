package com.mcb.javajuniortask.controller;

import com.mcb.javajuniortask.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class DebtController {
    @Autowired
    private DebtService debtService;

    @PostMapping(value = "/pay-the-debt")
    public ResponseEntity<?> payTheDebt(@RequestParam(value = "debtId") UUID debtId,
                                        @RequestParam(value = "value") BigDecimal value) {
        if (debtId == null || value == null) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok().body(debtService.payTheDebt(debtId, value));
    }
}
