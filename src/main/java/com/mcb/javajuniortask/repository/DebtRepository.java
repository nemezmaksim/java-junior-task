package com.mcb.javajuniortask.repository;

import com.mcb.javajuniortask.model.Debt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DebtRepository extends CrudRepository<Debt, UUID> {
}
