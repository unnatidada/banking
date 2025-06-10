package com.example.banking.mapper;

import com.example.banking.model.dto.AccountHolderDto;
import com.example.banking.model.entity.AccountHolder;
import org.springframework.stereotype.Component;

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
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
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
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		entity.setIsActive(dto.getIsActive());
		entity.setBalance(dto.getBalance());

		return entity;
	}
}
