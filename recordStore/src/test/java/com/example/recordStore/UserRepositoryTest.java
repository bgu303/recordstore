package com.example.recordStore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.recordStore.domain.User;
import com.example.recordStore.domain.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Test
	public void createNewUser() {
		User user = new User("testi", "testi", "testi@testi.com", "user");
		repository.save(user);
		
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewUser() {
		User user = repository.findByUsername("jukka");
		repository.delete(user);
		assertThat(repository.findByUsername("jukka")).isNull();
	}
}
