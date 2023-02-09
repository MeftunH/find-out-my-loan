package com.findoutmycreditscore.application.person.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
//No repository bean is used to prevent Spring Data from creating an instance of this interface.
@NoRepositoryBean
public interface PersonRepository extends JpaRepository<Person, Long>{
}
