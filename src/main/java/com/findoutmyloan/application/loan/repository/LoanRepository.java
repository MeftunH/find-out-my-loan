package com.findoutmyloan.application.loan.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query(value = "SELECT l.* FROM loan l JOIN person p ON l.customer_id = p.id WHERE p.identity_no = :identityNo AND p.birth_date = :birthDate", nativeQuery = true)
    List<Loan> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo,Date birthDate);

}
