package com.findoutmycreditscore.application.person.repository;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
//No repository bean is used to prevent Spring Data from creating an instance of this interface.
@NoRepositoryBean
public interface PersonRepository<T extends Person,L extends Long> extends JpaRepository<T, Long> {


}
