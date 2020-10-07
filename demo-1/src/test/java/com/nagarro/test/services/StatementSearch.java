package com.nagarro.test.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.security.sasl.AuthenticationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import com.nagarro.test.module.Account;
import com.nagarro.test.module.Statement;
import com.nagarro.test.module.StatementSearch;
import com.nagarro.test.repository.AccountRepository;
import com.nagarro.test.repository.StatementRepository;


@ExtendWith(MockitoExtension.class)
class StatementSearchTest {


	@Mock
	StatementRepository statementRepository;

	@Mock
	AccountRepository accountRepository;

	@Mock
	EntityManager entityManager;
	
	@InjectMocks
	StatementsServices statementsService;
	@BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock 
	       
	    }
	@Test
	void shouldReturnFindAll() throws AuthenticationException, NumberFormatException {
		StatementSearch statementSearch=new StatementSearch("saving","123456",LocalDate.parse("2020-01-01"),LocalDate.parse("2020-12-12"),null,null);
		List<Statement> statementsList=new ArrayList<Statement>();
		statementsList.add(new Statement(Long.parseLong("1"),Long.parseLong("1"),LocalDate.parse("2020-07-07"),"7"));
		when(accountRepository.findByAccountTypeAndAccountNumber("saving","123456")).thenReturn(Optional.of(new Account(Long.parseLong("1"),"saving","123456")));
		when(statementRepository.findByaccountId(Long.parseLong("1"))).thenReturn(statementsList.stream());

		
		assertEquals(statementsService.getStatementsDetials(statementSearch).get(0).getAccountId(),Long.parseLong("1"));
	}

}
