package com.nagarro.test.controllers;


import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.test.module.Statement;
import com.nagarro.test.module.StatementSearch;
import com.nagarro.test.services.StatementsServices;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StatmentController {
	
	@Autowired
	StatementsServices statementService;
    /**
     * {@code Post  /statement .
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Statements.
     */
	
	@Transactional
    @PostMapping("/statement")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getStatment(@Valid @RequestBody StatementSearch statementSearch) throws AuthenticationException{
		
		List<Statement> statementsList=statementService.gelStatements(statementSearch);    
        return new ResponseEntity<>(statementsList,HttpStatus.OK);
    }

  
}
