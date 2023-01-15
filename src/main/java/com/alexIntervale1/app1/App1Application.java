package com.alexIntervale1.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJms
@EnableAsync
public class App1Application {

	public static void main(String[] args) {
		SpringApplication.run(App1Application.class, args);
	}

}
