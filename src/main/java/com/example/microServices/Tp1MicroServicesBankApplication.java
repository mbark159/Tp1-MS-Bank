package com.example.microServices;

import com.example.microServices.entities.Account;
import com.example.microServices.entities.Customer;
import com.example.microServices.enums.AccountType;
import com.example.microServices.repositories.AccountRepository;
import com.example.microServices.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp1MicroServicesBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp1MicroServicesBankApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountRepository accountRepository, CustomerRepository customerRepository){
		return (args -> {

			Stream.of("Mohamed","yassine","hanae","imane").forEach(c->{
				Customer customer=Customer.builder()
						.name(c)
						.build();
				customerRepository.save(customer);

			});
			customerRepository.findAll().forEach(customer -> {
				for(int i=0;i<10;i++){
					Account account=Account.builder().id(UUID.randomUUID().toString())
							.type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
							.balance(10000+Math.random()+90000)
							.creatDate(new Date())
							.currency("MAD")
							.customer(customer)
							.build();
					accountRepository.save(account);
				}
			});


		});
	}
}
