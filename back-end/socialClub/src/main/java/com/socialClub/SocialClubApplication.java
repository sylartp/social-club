package com.socialClub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.socialClub.dao")
public class SocialClubApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialClubApplication.class, args);
	}
}
