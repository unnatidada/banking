package com.example.banking.service.impl;

import com.example.banking.mapper.accountHolderMapper;
import com.example.banking.model.dto.AccountHolderDto;
import com.example.banking.model.entity.AccountHolder;
import com.example.banking.repository.AccountHolderRepository;
import com.example.banking.service.AccountHolderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	private final AccountHolderRepository accountHolderRepository;
	private final accountHolderMapper accountHolderMapper;

	public AccountHolderServiceImpl(AccountHolderRepository accountHolderRepository, accountHolderMapper accountHolderMapper) {
		this.accountHolderRepository = accountHolderRepository;
		this.accountHolderMapper = accountHolderMapper;
	}

	@Override
	public AccountHolderDto getAccountHolder(Long id) {
		AccountHolder accountHolder = accountHolderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account with id %dnot found".formatted(id)));
		AccountHolderDto accountHolderDto = accountHolderMapper.toDto(accountHolder);
		return accountHolderDto;
	}

	@Override
	public List<AccountHolderDto> getAccountHolders() {
		List<AccountHolder> accountHolders = accountHolderRepository.findAll();
		return accountHolders.stream().map(accountHolderMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public AccountHolderDto addAccountHolder(AccountHolderDto accountHolderDto) {
		AccountHolder accountHolder = accountHolderMapper.toEntity(accountHolderDto);
		accountHolder.setCreatedAt(LocalDateTime.now());
		accountHolder.setUpdatedAt(LocalDateTime.now());
		accountHolder = accountHolderRepository.save(accountHolder);

		return accountHolderMapper.toDto(accountHolder);
	}

	@Override
	public AccountHolderDto updateAccountHolder(Long id, AccountHolderDto accountHolderDto) {
		AccountHolder existAccount =  accountHolderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account with id %dnot found".formatted(id)));
		AccountHolder accountHolder = accountHolderMapper.toEntity(accountHolderDto,existAccount);
		accountHolder = accountHolderRepository.save(accountHolder);
		return accountHolderMapper.toDto(accountHolder);
	}

	@Override
	public void deleteAccountHolder(Long id) {
		AccountHolder accountHolder = accountHolderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account with id %dnot found".formatted(id)));
		accountHolder.setIsActive(false);
		accountHolderRepository.save(accountHolder);
	}

	@Override
	public List<AccountHolderDto> getAccountHoldersByIsActive(Boolean isActive) {
		List<AccountHolder> accountHolders = accountHolderRepository.findByIsActive(isActive);
		return accountHolders.stream().map(accountHolderMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public AccountHolderDto deposit(Long id, Double amount) {
		AccountHolder accountHolder = accountHolderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account with id %dnot found".formatted(id)));
		if(!accountHolder.getIsActive()){
			throw new RuntimeException("Account is deActivated");
		}
		accountHolder.setBalance(accountHolder.getBalance() + amount);
		accountHolder = accountHolderRepository.save(accountHolder);
		return accountHolderMapper.toDto(accountHolder);
	}

	@Override
	public AccountHolderDto withdraw(Long id, Double amount) {
		AccountHolder accountHolder = accountHolderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account with id %dnot found".formatted(id)));
		if(!accountHolder.getIsActive()){
			throw new RuntimeException("Account is deActivated");
		}
		Double existingBalance = accountHolder.getBalance();
		if (existingBalance < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		accountHolder.setBalance(existingBalance - amount);
		accountHolder = accountHolderRepository.save(accountHolder);
		return accountHolderMapper.toDto(accountHolder);
	}
}
