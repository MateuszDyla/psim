package com.beereal.beerealbackend;

import com.beereal.beerealbackend.misc.DBFiller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BeerealbackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BeerealbackendApplication.class, args);
		DBFiller dbFiller = context.getBean(DBFiller.class);
		dbFiller.fill();
	}

}
