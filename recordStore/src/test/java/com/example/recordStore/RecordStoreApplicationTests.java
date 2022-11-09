package com.example.recordStore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.recordStore.web.RecordStoreEndPoints;
import com.example.recordStore.web.SessionEndPoints;
import com.example.recordStore.web.UserEndPoints;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecordStoreApplicationTests {

	@Autowired
	private SessionEndPoints sesEndpoints;

	@Autowired
	private RecordStoreEndPoints recEndpoints;

	@Autowired
	private UserEndPoints uEndpoints;

	@Test
	public void contextLoads() throws Exception {

		assertThat(sesEndpoints).isNotNull();

	}

	@Test
	public void contextLoadsTwo() throws Exception {

		assertThat(recEndpoints).isNotNull();

	}

	@Test
	public void contextLoadsThree() throws Exception {

		assertThat(uEndpoints).isNotNull();

	}

}
