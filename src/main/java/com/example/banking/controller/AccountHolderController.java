package com.example.banking.controller;

import com.example.banking.model.dto.AccountHolderDto;
import com.example.banking.service.AccountHolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountHolderController {

	private final AccountHolderService accountHolderService;

	public AccountHolderController(AccountHolderService accountHolderService) {this.accountHolderService = accountHolderService;}

	public ResponseEntity<List<AccountHolderDto>> getAllAccountHolders(){
		return new ResponseEntity<>(accountHolderService.getAccountHolders(),HttpStatus.OK);
	}
}
