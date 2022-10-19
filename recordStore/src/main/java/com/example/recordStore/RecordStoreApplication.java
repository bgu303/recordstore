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
			repository.deleteAll();
			Rec record1 = new Rec("007 / THE SCENE", "LP", "LANDSCAPES", "DETOUR", "EX", "EX", 25, "r3521240");
            Rec record2 = new Rec("10cc", "LP", "ART FOR ARTS SAKE", "J&B RECORDS/MERCURY", "EX", "VG+", 4, "r636809");
            Rec record3 = new Rec("10cc" , "LP", "HOW DARE YOU", "MERCURY", "VG", "VG", 3, "r2236095");
            Rec record4 = new Rec("49th PARALLEL", "LP", "49th PARALLEL", "bootleg", "EX", "EX", 30, "r3178159");
            Rec record5 = new Rec("5th DIMENSION", "LP", "LOVE'S LINES, ANGELS AND RHYME", "BELL", "EX", "EX", 3, "r402603");
            Rec record6 = new Rec("999", "12\"", "WAITING / ACTION (12\")", "LABRITAIN", "VG+", "VG", 8, "r2120964");
            Rec record7 = new Rec("A CERTAIN RATIO", "12\"", "DO THE DU (CASSE)", "FACTORY", "EX", "EX", 18, "r13050446");
            Rec record8 = new Rec("A CERTAIN RATIO", "12\"", "FLIGHT(12\")", "FACTORY", "EX-", "VG+", 15, "r3915466");
            Rec record9 = new Rec("ABC", "LP", "LEXICON OF LOVE", "VERTIGO", "EX", "VG+", 5, "r114525");
            Rec record10 = new Rec("AC/DC", "12\"", "WHO MADE WHO (PROMO)", "ATLANTIC", "EX", "VG", 10, "r6967323");
			
            repository.save(record1);
            repository.save(record2);
            repository.save(record3);
            repository.save(record4);
            repository.save(record5);
            repository.save(record6);
            repository.save(record7);
            repository.save(record8);
            repository.save(record9);
            repository.save(record10);
			
		};
	}

}
