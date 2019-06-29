package com.hero.hotel.main;

		import org.mybatis.spring.annotation.MapperScan;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.web.servlet.ServletComponentScan;
		import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.hero.hotel.controller","com.hero.hotel.service", "com.hero.hotel.config"})
@MapperScan("com.hero.hotel.dao")
@ServletComponentScan("com.hero.hotel.filter")
public class HeroHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroHotelApplication.class, args);
	}

}
