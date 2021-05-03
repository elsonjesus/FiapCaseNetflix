package com.fiap.appcase.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.appcase.netflix.entity.Category;
import com.fiap.appcase.netflix.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/netflix")
public class CategoryController {

	@Autowired
	private CategoryRepository categoriaRepository;

	@GetMapping(value={"genre","genres","movie/genre","movies/genre","movie/mostview","movies/mostview"})
	public List<Category> getAllCategoria() {
		return categoriaRepository.findAll();
	}

	@GetMapping(value={"genre/{id}","genres/{id}"})
	public List<Category> getFindOne(@PathVariable(name = "id") Long id) {
		return categoriaRepository.findById(id);
	}
	
}
