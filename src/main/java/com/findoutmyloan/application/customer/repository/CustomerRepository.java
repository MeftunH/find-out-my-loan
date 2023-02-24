package com.findoutmyloan.application.customer.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.person.entity.Person;
import com.findoutmyloan.application.person.repository.PersonRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CustomerRepository extends PersonRepository {
    Optional<Customer> findByIdentityNo(Long id);
    Optional<Person> findByIdentityNoAndBirthDate(Long id, Date birthDate);
    @Transactional
    void deleteByIdentityNo(long identityNo);
}
