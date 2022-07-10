package com.an.restdemo;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.an.service.FilesStorageService;

@SpringBootApplication(scanBasePackages={"com.an"})
public class RestDemoApplication implements CommandLineRunner {

	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	}
}
