package pl.koziolekweb.idegosuperego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdEgoSuperegoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(IdEgoSuperegoApplication.class);
		app.run(args);
	}

}
