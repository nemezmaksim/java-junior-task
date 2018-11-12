package com.mcb.javajuniortask.service;

import com.mcb.javajuniortask.model.Debt;
import com.mcb.javajuniortask.model.Payment;
import com.mcb.javajuniortask.repository.DebtRepository;
import com.mcb.javajuniortask.repository.PaymentRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@ShellComponent
public class DebtService {
    private final DebtRepository debtRepository;
    private final PaymentRepository paymentRepository;

    public DebtService(DebtRepository debtRepository, PaymentRepository paymentRepository) {
        this.debtRepository = debtRepository;
        this.paymentRepository = paymentRepository;
    }

    @ShellMethod("Pay for specified client debt")
    @Transactional
    public UUID payTheDebt(@ShellOption UUID debtId, @ShellOption BigDecimal payValue) {
        Debt debt = Optional.ofNullable(debtRepository
                .findOne(debtId))
                .orElseThrow(() -> new RuntimeException("debtid not found"));
        Optional.ofNullable(payValue).
                filter(p -> p.compareTo(BigDecimal.ZERO) > 0 && payValue.compareTo(debt.getValue()) <= 0).
                orElseThrow(
                        () -> new IllegalArgumentException("payValue is not valid"));
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());
        payment.setValue(payValue);
        payment.setDebt(debt);
        paymentRepository.save(payment);
        debt.setValue((debt.getValue().subtract(payValue)));
        debtRepository.save(debt);
        return payment.getId();
    }
}
