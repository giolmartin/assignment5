package com.meritamerica.assignment5.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.meritamerica.assignment5.exceptions.ExceedsAvailableBalanceException;
import com.meritamerica.assignment5.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.*;

@RestController
public class MeritBankController {

	List<AccountHolder> accountHolders = new ArrayList<AccountHolder>();
	List<CDOffering> cdOfferings = new ArrayList<CDOffering>();
	
	@PostMapping(value = "/AccountHolder")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
		MeritBank.addAccountHolder(accountHolder);
		return accountHolder;
	}
	
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccount")
	public void addDeposit(@PathVariable ("id") int id, @RequestParam double deposit) 
							throws ExceedsAvailableBalanceException, 
													 ExceedsCombinedBalanceLimitException {
		accountHolders = MeritBank.getAccountHolders();

			if (accountHolders.get(id - 1).getCombinedBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Exceeds Account Balance Limit");
			}
			if(deposit < 0) {
				throw new ExceedsAvailableBalanceException("Deposit less than 0");
			}
			accountHolders.get(id - 1).addCheckingAccount(deposit);
	}
}
