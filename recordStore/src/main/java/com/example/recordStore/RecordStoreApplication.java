package com.example.recordStore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.recordStore.domain.Rec;
import com.example.recordStore.domain.RecRepository;

@SpringBootApplication
public class RecordStoreApplication {
	
	//testing this shit

	public static void main(String[] args) {
		SpringApplication.run(RecordStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(RecRepository repository) {
		return (args) -> {
			Rec record1 = new Rec("AAAAAA", "LP", "LANDSCAPES", "DETOUR", "EX",	"EX", 25, "r3521240");
			
			repository.save(record1);
			
		};
	}

}
