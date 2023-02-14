package com.findoutmyloan.application.loan.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    //get customer's amount by customer id
    @Query(value = "SELECT c.monthly_income FROM customer c JOIN loan l ON c.id =  l.customer_id WHERE l.customer_id = :customerId",
            nativeQuery = true)
    float findMonthlyIncomeByCustomerId(@Param("customerId") Long customerId);


}
