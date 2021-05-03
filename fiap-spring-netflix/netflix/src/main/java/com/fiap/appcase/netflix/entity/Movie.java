package com.fiap.appcase.netflix.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
@SecondaryTable(name = "category")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryid", referencedColumnName = "id")
	private Category categoryname;
	
	@Column(name = "liked", nullable = true)
	private boolean liked;
	
	@Column(name = "view")
	private boolean assistido;
	
	@Column(name = "future")
	private boolean assistirdepois;
	
	public Movie() {
	}

	public Movie(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category getCategoryName() {
		return categoryname;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	
	public boolean isAssistido() {
		return assistido;
	}

	public void setView(boolean assistido) {
		this.assistido = assistido;
	}

	public boolean isAssistirDepois() {
		return assistirdepois;
	}

	public void setFuture(boolean assistirdepois) {
		this.assistirdepois = assistirdepois;
	}
	
}
