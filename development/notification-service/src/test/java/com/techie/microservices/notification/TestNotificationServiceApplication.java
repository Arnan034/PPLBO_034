package com.techie.microservices.notification;

import org.springframework.boot.SpringApplication;

import com.techie.microservices.B8.notification.NotificationServiceApplication;

public class TestNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(NotificationServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
