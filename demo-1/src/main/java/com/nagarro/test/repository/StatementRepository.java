package com.nagarro.test.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.test.module.Statement;
import org.springframework.data.jpa.repository.QueryHints;
import javax.persistence.QueryHint;
public interface StatementRepository extends JpaRepository< Statement,Long>{
	
	@Query("select b from Statement b where b.accountId=:accountId")
	Stream<Statement> findByaccountId(Long accountId);
}
