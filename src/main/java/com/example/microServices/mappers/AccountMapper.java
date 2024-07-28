package com.example.microServices.mappers;

import com.example.microServices.dtos.BankAccountRequestDTO;
import com.example.microServices.dtos.BankAccountResponseDTO;
import com.example.microServices.entities.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BankAccountResponseDTO fromBankAccount(Account account){
        BankAccountResponseDTO bankAccountResponseDTO=new BankAccountResponseDTO();
        BeanUtils.copyProperties(account,bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
