package com.example.microServices.entities;

import com.example.microServices.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Account.class,name = "p1")
public interface AccountProjection {
    public  String getId();
    public AccountType getType();
    public Double getBalance();
}
