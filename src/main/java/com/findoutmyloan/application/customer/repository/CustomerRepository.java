package com.findoutmyloan.application.customer.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.person.entity.Person;
import com.findoutmyloan.application.person.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends PersonRepository {
    Optional<Person> findByIdentityNo(Long id);
}
