package com.example.microServices.web;

import com.example.microServices.dtos.BankAccountRequestDTO;
import com.example.microServices.dtos.BankAccountResponseDTO;
import com.example.microServices.entities.Account;
import com.example.microServices.entities.Customer;
import com.example.microServices.repositories.AccountRepository;
import com.example.microServices.repositories.CustomerRepository;
import com.example.microServices.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired

    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired

    private CustomerRepository customerRepository;

    @QueryMapping
    public List<Account> accountsList(){
        return  accountRepository.findAll();
    }

    @QueryMapping
    public Account bankAccountById(@Argument String id){
        return  accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
       accountRepository.deleteById(id);
       return true;
    }
    @QueryMapping
    public  List<Customer> customers(){
        return customerRepository.findAll();
    }

}

/* record BankAccountDTO(Double balance,String type,String currency){

}*/
