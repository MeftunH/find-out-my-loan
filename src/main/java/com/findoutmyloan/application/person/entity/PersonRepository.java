package com.findoutmyloan.application.person.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByIdentityNo(Long identityNo);

    Person findByPhoneNumber(String phoneNumber);
}