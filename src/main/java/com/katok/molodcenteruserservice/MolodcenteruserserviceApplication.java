package com.katok.molodcenteruserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class MolodcenteruserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MolodcenteruserserviceApplication.class, args);
	}

}
