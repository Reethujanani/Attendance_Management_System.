package com.example.attendance;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AttendanceMgmtApplication {
	private static final Logger LOGGER= (Logger) LoggerFactory.getLogger(AttendanceMgmtApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(AttendanceMgmtApplication.class, args);


		LOGGER.info("Username Already available");
		LOGGER.info( "user saved successfully");
		LOGGER.info("Error while saving user");
		LOGGER.info("user update successfully");
		LOGGER.info("user deleted successfully");
		

	}

}
