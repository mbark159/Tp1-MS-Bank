package com.example.microServices.services;

import com.example.microServices.dtos.BankAccountRequestDTO;
import com.example.microServices.dtos.BankAccountResponseDTO;


public interface AccountService {
   public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
