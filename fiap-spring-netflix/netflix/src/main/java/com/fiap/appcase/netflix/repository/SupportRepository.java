package com.fiap.appcase.netflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.appcase.netflix.entity.Support;

@Repository
public interface SupportRepository extends JpaRepository<Support, String> {

	Support findById(Long id);

}
