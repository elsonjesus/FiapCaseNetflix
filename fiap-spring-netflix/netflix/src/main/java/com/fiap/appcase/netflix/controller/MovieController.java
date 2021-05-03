package com.fiap.appcase.netflix.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fiap.appcase.netflix.entity.Category;
import com.fiap.appcase.netflix.entity.Movie;
import com.fiap.appcase.netflix.rabbitmq.CustomMessage;
import com.fiap.appcase.netflix.rabbitmq.MQConfig;
import com.fiap.appcase.netflix.service.CacheService;
import com.fiap.appcase.netflix.redis.Cache;
import com.fiap.appcase.netflix.repository.CategoryRepository;
import com.fiap.appcase.netflix.repository.MovieRepository;

@RestController
@RequestMapping(value = "/netflix")
public class MovieController {
	
	int count = 0;
	Long categoryParsed, idParsed;
	
	@Autowired
	private RabbitTemplate template;
	@Autowired
	private MovieRepository filmeRepository;
	@Autowired
    private CacheService cacheService;
	@Autowired
	private CategoryRepository categoriaRepository;
	
	@GetMapping(value={"movie","movies"})
	public List<Movie> getAllFilme() {
		return filmeRepository.findAll();
	}

	@GetMapping(value = { "movie/{id}", "movies/{id}" })
	public Movie getFindOne(@PathVariable(name = "id") Long id) {
		Movie filme = filmeRepository.findById(id);

		CustomMessage message = new CustomMessage();
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		message.setMessage(filme.getId());
		template.convertAndSend(MQConfig.EXCHANGE_LISTENER, MQConfig.ROUTING_KEY, message);

		if (cacheService.fetchUserById(id) != null) {
			count = cacheService.fetchUserById(id).getVisto();
			count += 1;
		} else {
			count = 1;
		};

		Cache cache = new Cache();
		cache.setId(filme.getId());
		cache.setFilme(filme.getName());
		cache.setCategoria(filme.getCategoryName().getName());
		cache.setVisto(count);
		cacheService.saveUser(cache);
		return filme;
	}

	@GetMapping(value={"movie/genre/{categoryId}","movies/genre/{categoryId}"})
	public List<Movie> getFilmeByCategoria(@PathVariable(name = "categoryId") String categoryId) {

		List<Movie> resultFilm = filmeRepository.findAll();
		List<Movie> filmes = new ArrayList<Movie>();
	

		try {
			categoryParsed = Long.parseLong(categoryId);
		   	for (int i = 0; i < resultFilm.size(); i++) {
				if (resultFilm.get(i).getCategoryName().getId() == categoryParsed) {
					filmes.add(resultFilm.get(i));
				}
			}
	    } catch (NumberFormatException nfe) {
	    	for (int i = 0; i < resultFilm.size(); i++) {
				if (resultFilm.get(i).getCategoryName().getName().equalsIgnoreCase(categoryId)) {
					filmes.add(resultFilm.get(i));
				}
			}
	    }
		return filmes;
	}
	
	@GetMapping(value={"movie/search/{name}","movies/search/{name}"})
	public List<Movie> getLikeName(@PathVariable(name = "name") String name) {

		List<Movie> filme = filmeRepository.findByNameLikeIgnoreCase("%" + name + "%");
		return filme;
	}

	@GetMapping(value={"movie/watched","movies/watched"})
	public List<Movie> getFilmeView() {
		return filmeRepository.findByAssistido(true);
	}

	@GetMapping(value={"movie/watched/{categoryId}","movies/watched/{categoryId}"})
	public List<Movie> getFilmeViewCategoria(@PathVariable(name = "categoryId") Long categoryId) {

		List<Movie> resultFilm = filmeRepository.findByAssistido(true);
		List<Movie> filmes = new ArrayList<Movie>();

		for (int i = 0; i < resultFilm.size(); i++) {

			if (resultFilm.get(i).getCategoryName().getId() == categoryId) {
				filmes.add(resultFilm.get(i));
			}
		}
		return filmes;
	}
	
	@GetMapping(value={"movie/mostview/{categoryId}","movies/moreview/{categoryId}"})
	public List<Cache> getFindOneCategoria(@PathVariable(name = "categoryId") String categoryId) {
		
        List<Cache> resultCache = cacheService.fetchAllFilme();
		List<Cache> caches = new ArrayList<Cache>();

		try {
			categoryParsed = Long.parseLong(categoryId);
			int categoryParsedInt = categoryParsed.intValue();
			categoryParsedInt -= 1;
	    	List<Category> categorias = categoriaRepository.findAll();
		   	for (int i = 0; i < resultCache.size(); i++) {
		   		if (resultCache.get(i).getCategoria().equalsIgnoreCase(categorias.get(categoryParsedInt).getName())) {
					caches.add(resultCache.get(i));
			}
		  }
	    } catch (NumberFormatException nfe) {
	    	for (int i = 0; i < resultCache.size(); i++) {
				if (resultCache.get(i).getCategoria().equalsIgnoreCase(categoryId)) {
					caches.add(resultCache.get(i));
				}
			}
	    }
		return caches;
	}

	@PutMapping("movie/liked/{id}")
	public ResponseEntity<String> setLiked(@PathVariable(name = "id") Long id) {

		Movie filme = filmeRepository.findById(id);
		filme.setLiked(true);

		filmeRepository.save(filme);
		
		boolean result = filmeRepository.save(filme).isLiked();
        if(result)
            return ResponseEntity.ok("Very well, you liked movie "+ "'"+filme.getName()+"'" +" ");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	

	@PutMapping(value={"movie/watched/{id}","movie/watched/{id}"})
	public ResponseEntity<String> setView(@PathVariable(name = "id") Long id) {

		Movie filme = filmeRepository.findById(id);
		filme.setView(true);
		filme.setFuture(false);

		filmeRepository.save(filme);

		boolean result = filmeRepository.save(filme).isAssistido();
        if(result)
            return ResponseEntity.ok("The movie "+ "'"+filme.getName()+"'" +" marked as already watched. ");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(value={"movie/watchlater/{id}","movies/watchlater/{id}"})
	public ResponseEntity<String> setFuture(@PathVariable(name = "id") Long id) {

		Movie filme = filmeRepository.findById(id);
		filme.setFuture(true);

		filmeRepository.save(filme);
		
		boolean result = filmeRepository.save(filme).isAssistirDepois();
        if(result)
            return ResponseEntity.ok("Movie "+ "'"+filme.getName()+"'" +" scheduled to watch later. ");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}