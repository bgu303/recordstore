package com.example.recordStore;

import java.io.FileReader;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.recordStore.domain.Rec;
import com.example.recordStore.domain.RecRepository;
import com.example.recordStore.domain.User;
import com.example.recordStore.domain.UserRepository;
import com.opencsv.bean.CsvToBeanBuilder;

@SpringBootApplication
public class RecordStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RecRepository repository, UserRepository urepository) {
		return (args) -> {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
			repository.deleteAll();

			String fileName = "malli-3Fixed-FINAL.txt";

			List<Rec> recs = (List<Rec>) new CsvToBeanBuilder<Rec>(new FileReader(fileName)).withType(Rec.class).build()
					.parse();

			for (Rec rec : recs) {
				repository.save(rec);
			}
		};

	};
};
