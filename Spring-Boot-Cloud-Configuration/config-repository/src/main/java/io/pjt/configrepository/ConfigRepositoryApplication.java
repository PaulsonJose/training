package io.pjt.configrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigRepositoryApplication.class, args);
	}

}
