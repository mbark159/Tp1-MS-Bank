package com.example.microServices.services;

import com.example.microServices.dtos.BankAccountRequestDTO;
import com.example.microServices.dtos.BankAccountResponseDTO;
import com.example.microServices.entities.Account;
import com.example.microServices.mappers.AccountMapper;
import com.example.microServices.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service @Transactional
public class AccountServiceImpl implements AccountService {
   @Autowired
   AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        Account account=Account.builder()
                .id(UUID.randomUUID().toString())
                .creatDate(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
       Account saveAccount= accountRepository.save(account);
       BankAccountResponseDTO bankAccountResponseDTO= accountMapper.fromBankAccount(saveAccount);
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountDTO) {
        Account account=Account.builder()
                .id(id)
                .creatDate(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        Account saveAccount= accountRepository.save(account);
        BankAccountResponseDTO bankAccountResponseDTO= accountMapper.fromBankAccount(saveAccount);
        return bankAccountResponseDTO;
    }
}
