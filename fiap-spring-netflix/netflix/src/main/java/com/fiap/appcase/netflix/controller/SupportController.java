package com.fiap.appcase.netflix.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fiap.appcase.netflix.entity.Support;
import com.fiap.appcase.netflix.repository.SupportRepository;

@RestController
@RequestMapping(value = "/netflix")
public class SupportController {

	@Autowired
	private SupportRepository suporteRepository;

	@GetMapping("/support")
	public List<Support> getAllSuporte() {
		return suporteRepository.findAll();
	}

	@GetMapping("/support/{id}")
	public Support getFindOne(@PathVariable(name = "id") Long id) {
		return suporteRepository.findById(id);
	}

	@PostMapping("/support")
	public Support createSuporte(@Valid @RequestBody Support suporte) {
		return suporteRepository.save(suporte);
	}

}