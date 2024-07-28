package com.example.microServices.web;

import com.example.microServices.dtos.BankAccountRequestDTO;
import com.example.microServices.dtos.BankAccountResponseDTO;
import com.example.microServices.entities.Account;
import com.example.microServices.mappers.AccountMapper;
import com.example.microServices.repositories.AccountRepository;
import com.example.microServices.services.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")

public class AccountRestController {
    private AccountRepository accountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;
    public AccountRestController(AccountRepository accountRepository, AccountService accountService, AccountMapper accountMapper){
        this.accountRepository=accountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/accounts")
    public List<Account> accounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    public Account account(@PathVariable String id){
        return accountRepository.findById(id).orElseThrow(() ->
            new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/accounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/account/{id}")
   public  Account update(@PathVariable String id,@RequestBody Account bankAccount){
        Account account=accountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getType()!=null)account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getCreatDate()!=null) account.setCreatDate(new Date());
        return accountRepository.save(account);

   }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable String id){
         accountRepository.deleteById(id);
    }
}
