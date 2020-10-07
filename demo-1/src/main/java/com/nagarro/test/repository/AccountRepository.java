package com.nagarro.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.test.module.Account;

public interface AccountRepository extends JpaRepository< Account, Long>{
	Optional<Account> findByAccountTypeAndAccountNumber(String accountType,String accountNumber);
}
