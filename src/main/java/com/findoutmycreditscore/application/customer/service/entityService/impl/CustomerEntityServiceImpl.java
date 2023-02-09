package com.findoutmycreditscore.application.customer.service.entityService.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.customer.repository.CustomerRepository;
import com.findoutmycreditscore.application.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerEntityServiceImpl {
    private final CustomerRepository customerRepository;

    public List<Person> findAll() {
        List<Person> persons = customerRepository.findAll();
        return persons;
    }
}
