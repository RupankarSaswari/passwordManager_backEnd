package com.example.Account;

import com.example.Account.AccountCreation.AccountCreateController;
import com.example.Account.HashingUtil.HashingUtil;
import com.example.Account.JDBC.LoginRepo;
import com.example.Account.Login.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		ApplicationContext context = SpringApplication.run(AccountApplication.class, args);
//
//		LoginRepo loginRepo = context.getBean(LoginRepo.class);
//		loginRepo.dropTable("rupankarinfo");
//		Boolean b = loginRepo.getTable("rupankar");
//		System.out.println(b);

//		LoginController loginController = context.getBean(LoginController.class);
//		loginController.loginController("rupankar","704498");
//
//		AccountCreateController accountCreateController = context.getBean(AccountCreateController.class);
//		accountCreateController.accountCreateController("rupankar","704498");
//
//		HashingUtil hashingUtil = new HashingUtil();
//		System.out.println(hashingUtil.getHashPassword("704498"));

	}

}
