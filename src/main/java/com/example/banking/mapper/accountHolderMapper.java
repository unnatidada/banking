package com.example.banking.mapper;

import com.example.banking.model.dto.AccountHolderDto;
import com.example.banking.model.entity.AccountHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class accountHolderMapper {
	public AccountHolderDto toDto(AccountHolder entity) {
		if (entity == null) {
			return null;
		}

		AccountHolderDto dto = new AccountHolderDto();
		dto.setId(entity.getId());
		dto.setAccountHolderName(entity.getAccountHolderName());
		dto.setType(entity.getType());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String createdAt = entity.getCreatedAt().format(formatter);
		String updatedAt = entity.getUpdatedAt().format(formatter);
		dto.setCreatedAt(createdAt);
		dto.setUpdatedAt(updatedAt);
		dto.setIsActive(entity.getIsActive());
		dto.setBalance(entity.getBalance());

		return dto;
	}

	public AccountHolder toEntity(AccountHolderDto dto) {
		if (dto == null) {
			return null;
		}

		AccountHolder entity = new AccountHolder();
		entity.setId(dto.getId());
		entity.setAccountHolderName(dto.getAccountHolderName());
		entity.setType(dto.getType());
		entity.setIsActive(dto.getIsActive());
		entity.setBalance(dto.getBalance());

		return entity;
	}

	public AccountHolder toEntity(AccountHolderDto dto,AccountHolder existAccountHolder) {
		if (dto == null) {
			return null;
		}

		AccountHolder entity = new AccountHolder();

		// Prioritize DTO value, else fallback to existing value
		entity.setId(dto != null && dto.getId() != null ? dto.getId() : existAccountHolder.getId());
		entity.setAccountHolderName(dto != null && dto.getAccountHolderName() != null ? dto.getAccountHolderName() : existAccountHolder.getAccountHolderName());
		entity.setType(dto != null && dto.getType() != null ? dto.getType() : existAccountHolder.getType());
		entity.setCreatedAt(existAccountHolder.getCreatedAt());
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setIsActive(dto != null && dto.getIsActive() != null ? dto.getIsActive() : existAccountHolder.getIsActive());
		entity.setBalance(dto != null && dto.getBalance() != 0 ? dto.getBalance() : existAccountHolder.getBalance());

		return entity;
	}
}
