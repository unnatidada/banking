package com.example.banking.service;

import com.example.banking.model.dto.AccountHolderDto;
import com.example.banking.model.entity.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderService {
	AccountHolderDto getAccountHolder(Long id);
	List<AccountHolderDto> getAccountHolders();
	AccountHolderDto addAccountHolder(AccountHolderDto accountHolderDto);
	AccountHolderDto updateAccountHolder(Long id, AccountHolderDto accountHolderDto);
	void deleteAccountHolder(Long id);
	List<AccountHolderDto> getAccountHoldersByIsActive(Boolean isActive);
	AccountHolderDto deposit(Long id, Double amount);
	AccountHolderDto withdraw(Long id, Double amount);
}
