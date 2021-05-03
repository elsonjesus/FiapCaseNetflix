package com.fiap.appcase.netflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.appcase.netflix.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
	
	Movie findById(Long id);
	List<Movie> findByNameLikeIgnoreCase(String name);
	List<Movie> findByAssistido(boolean assistido);

}
