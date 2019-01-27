package com.rasto.stockscreener;

import com.rasto.stockscreener.data.entity.User;
import com.rasto.stockscreener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StockScreenerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StockScreenerApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {
		User user = new User();
		user.setUsername("test");
		user.setPassword(passwordEncoder.encode("test"));
		userService.save(user);
	}
}

