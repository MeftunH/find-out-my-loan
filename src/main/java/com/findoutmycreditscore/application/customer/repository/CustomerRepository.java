package com.findoutmycreditscore.application.customer.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.person.entity.Person;
import com.findoutmycreditscore.application.person.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends PersonRepository {
    Optional<Person> findByIdentityNo(Long id);
}
