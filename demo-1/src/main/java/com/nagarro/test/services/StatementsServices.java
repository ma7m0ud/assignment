package com.nagarro.test.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.nagarro.test.module.Account;
import com.nagarro.test.module.Statement;
import com.nagarro.test.module.StatementSearch;
import com.nagarro.test.module.URole;
import com.nagarro.test.repository.AccountRepository;
import com.nagarro.test.repository.StatementRepository;

@Service
public class StatementsServices {

	@Autowired
	StatementRepository statementRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	EntityManager entityManager;

	public List<Statement> gelStatements(StatementSearch statementSearch) throws AuthenticationException {
		
		System.out.println("userDetails");
		LogedInUser userDetails = (LogedInUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		boolean hasUserRole = userDetails.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals(URole.ROLE_USER.name()));
		
		System.out.println(hasUserRole);
		if(hasUserRole&&(statementSearch.getFromDate()!=null||statementSearch.getToDate()!=null
				||statementSearch.getFromAmount()!=null||statementSearch.getToAmount()!=null)) {
			System.out.println("ROLE USER");
			throw new AuthenticationException("Error: Unauthorized");
		}
		
		return getStatementsDetials(statementSearch);
	}
		
	public List<Statement> getStatementsDetials(StatementSearch statementSearch) {
		
		if(statementSearch.getFromDate()==null&&statementSearch.getToDate()==null) {
			LocalDate fromDate=LocalDate.now();
			statementSearch.setFromDate(fromDate.minusDays(1));
			statementSearch.setToDate(fromDate.minusMonths(3));
		}
		
		List<Statement> statementsList = new ArrayList<>();
		Account account = accountRepository
				.findByAccountTypeAndAccountNumber(statementSearch.getAccountType(), statementSearch.getAccountNumber())
				.orElseThrow(() -> new RuntimeException("Error: Invalid Account"));
		Stream<Statement> statements = statementRepository.findByaccountId(account.getID());
		statements.forEach(statement -> {
			if (isBetweenDate(statementSearch.getFromDate().minusDays(1), statementSearch.getToDate().plusDays(1), statement.getDatefield())) {
				if (isBetweenAmount(statementSearch.getFromAmount(), statementSearch.getToAmount(),
						Double.parseDouble(statement.getAmount()))) {
					statementsList.add(statement);
				}
			}
			entityManager.detach(statement);
		});
		return statementsList;
	}
		
	public boolean isBetweenDate(LocalDate start, LocalDate end, LocalDate date) {
		return (start == null || !date.isBefore(start.minusDays(1))) && (end == null || !date.isAfter(end.plusDays(1)));
	}

	public boolean isBetweenAmount(Double start, Double end, Double amount) {
		return (start == null || !(amount <= start)) && (end == null || !(amount >= end));
	}
}
