package com.fiap.appcase.netflix.redis;

import lombok.Data;
import java.io.Serializable;

@Data
public class Cache implements Serializable {

	private Long id;
    private String filme;
    private String categoria;
    private int visto;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilme() {
		return filme;
	}
	public void setFilme(String filme) {
		this.filme = filme;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getVisto() {
		return visto;
	}
	public void setVisto(int visto) {
		this.visto = visto;
	}
    
}
