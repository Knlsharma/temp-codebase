package com.trans.svc;

import org.springframework.boot.SpringApplication;

public class TestSvcApplication {

	public static void main(String[] args) {
		SpringApplication.from(SvcApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
