package com.safetynet.alert;

import com.safetynet.alert.controller.GetControllers.ChildController;
import com.safetynet.alert.controller.GetControllers.FireStationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertApplication implements CommandLineRunner {

	@Autowired
	private FireStationController tc;
	@Autowired
	private ChildController cc;

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
