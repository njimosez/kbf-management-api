package com.kbf.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kbf.management.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT * FROM transactions t " + "WHERE t.date >= (CURRENT_DATE - CAST(:interval AS INTERVAL)) "
			+ "ORDER BY t.date ASC", nativeQuery = true)
	List<Transaction> findTransactionsFromInterval(@Param("interval") String interval);

	@Query(value = "SELECT * FROM transactions t " + "WHERE t.date >= :startDate "
			+ "ORDER BY t.date ASC", nativeQuery = true)
	List<Transaction> findTransactionsFromStartDate(@Param("startDate") LocalDate startDate);

}
