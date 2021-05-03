package com.fiap.appcase.netflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.appcase.netflix.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	
	List<Category> findById(Long id);

}
