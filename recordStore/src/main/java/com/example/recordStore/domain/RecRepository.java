package com.example.recordStore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecRepository extends CrudRepository<Rec, Long> {
	List<Rec> findByDiscogs(@Param("discogs") String discogs);
}
