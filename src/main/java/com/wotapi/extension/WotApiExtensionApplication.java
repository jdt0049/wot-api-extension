package com.wotapi.extension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WotApiExtensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WotApiExtensionApplication.class, args);
	}

}
