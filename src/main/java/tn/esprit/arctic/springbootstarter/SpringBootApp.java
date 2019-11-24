package tn.esprit.arctic.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="tn.esprit.arctic.*")
public class SpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);

	}

}
