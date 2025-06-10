package com.example.banking.controller;

import com.example.banking.model.dto.AccountHolderDto;
import com.example.banking.service.AccountHolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountHolderController {

	private final AccountHolderService accountHolderService;

	public AccountHolderController(AccountHolderService accountHolderService) {this.accountHolderService = accountHolderService;}

	@GetMapping("/getAll")
	public ResponseEntity<List<AccountHolderDto>> getAllAccountHolders() {
		return new ResponseEntity<>(accountHolderService.getAccountHolders(), HttpStatus.OK);
	}

	@GetMapping("/getAccountById/{id}")
	public ResponseEntity<AccountHolderDto> getAccountById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(accountHolderService.getAccountHolder(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<AccountHolderDto> addAccount(@RequestBody AccountHolderDto accountHolderDto) {
		return new ResponseEntity<>(accountHolderService.addAccountHolder(accountHolderDto), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AccountHolderDto> updateAccount(@PathVariable("id") Long id, @RequestBody AccountHolderDto accountHolderDto) {
		return new ResponseEntity<>(accountHolderService.updateAccountHolder(id, accountHolderDto), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id) {
		accountHolderService.deleteAccountHolder(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/deposit/{id}/{amount}")
	public ResponseEntity<AccountHolderDto> deposite(@PathVariable("id") Long id, @PathVariable("amount") Double amount) {
		return new ResponseEntity<>(accountHolderService.deposit(id, amount), HttpStatus.OK);
	}

	@GetMapping("/withdraw/{id}/{amount}")
	public ResponseEntity<AccountHolderDto> withdraw(@PathVariable("id") Long id, @PathVariable("amount") Double amount) {
		return new ResponseEntity<>(accountHolderService.withdraw(id, amount), HttpStatus.OK);
	}

	@GetMapping("/getAllByStatus/{isActive}")
	public ResponseEntity<List<AccountHolderDto>> getAllAccountHoldersByStatus(@PathVariable("isActive") Boolean isActive) {
		return new ResponseEntity<>(accountHolderService.getAccountHoldersByIsActive(isActive), HttpStatus.OK);
	}

}
