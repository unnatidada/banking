package com.example.banking.repository;

import com.example.banking.model.entity.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

	List<AccountHolder> findByIsActive(Boolean isActive);
}
