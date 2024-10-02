package com.souleimen.avions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.souleimen.avions.entities.Avion;
import com.souleimen.avions.entities.TypeAv;

@SpringBootApplication
public class AvionsApplication implements CommandLineRunner {
	
		@Autowired
		private RepositoryRestConfiguration repositoryRestConfiguration;
		
		public static void main(String[] args) {
			SpringApplication.run(AvionsApplication.class, args);
		}
		
		@Override
		public void run(String... args) throws Exception {	
			repositoryRestConfiguration.exposeIdsFor(Avion.class,TypeAv.class);
		}
	}
