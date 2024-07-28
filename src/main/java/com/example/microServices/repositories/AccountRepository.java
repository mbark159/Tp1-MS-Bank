package com.example.microServices.repositories;

import com.example.microServices.entities.Account;
import com.example.microServices.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
@RepositoryRestResource

public interface AccountRepository extends JpaRepository<Account,String> {
    // pour creer un alias pour le parametre type t au lieu de type
    @RestResource(path = "/byType")
    List<Account> findByType(@Param("t") AccountType type);
}
