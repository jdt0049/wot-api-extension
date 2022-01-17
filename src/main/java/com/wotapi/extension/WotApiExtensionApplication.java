package com.wotapi.extension;

import com.wotapi.extension.model.Gamer;
import com.wotapi.extension.repository.GamerRepository;
import com.wotapi.extension.service.DataRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;

@EnableScheduling
@SpringBootApplication
public class WotApiExtensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WotApiExtensionApplication.class, args);
	}

	@Autowired
	private DataRefreshService dataRefreshService;
	@Autowired
	GamerRepository gamerRepository;

	@PostConstruct
	void run() throws URISyntaxException, InterruptedException {
		Gamer gamer = new Gamer(1015954670, null, null, null);
		gamerRepository.save(gamer);
		dataRefreshService.refreshGamerData();
		System.out.println(gamerRepository.findAll());
	}
}
