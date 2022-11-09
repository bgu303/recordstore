package com.example.recordStore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.recordStore.domain.Rec;
import com.example.recordStore.domain.RecRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RecRepositoryTest {

	@Autowired
	private RecRepository repository;

	@Test
	public void findByDiscogsShouldReturnRecord() {
		List<Rec> records = repository.findByDiscogs("r2052863");

		assertThat(records).hasSize(1);
		assertThat(records.get(0).getTitle()).isEqualTo("WHEN YOUR HEARTS STOPS BEATING");

	}
	
	@Test
	public void createNewRec() {
		Rec rec = new Rec("Artist", "LP", "Title", "Labeli", "ex", "vg", 12, "d012334", "rock");
		repository.save(rec);
		
		assertThat(rec.getId()).isNotNull();
	}

}
