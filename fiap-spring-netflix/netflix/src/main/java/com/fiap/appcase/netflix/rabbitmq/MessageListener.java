package com.fiap.appcase.netflix.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fiap.appcase.netflix.entity.Movie;
import com.fiap.appcase.netflix.service.CacheService;
import com.fiap.appcase.netflix.redis.Cache;
import com.fiap.appcase.netflix.repository.MovieRepository;

@Component
public class MessageListener {
	int count = 0;
	
	@Autowired
    private CacheService cacheService;
	@Autowired
	private MovieRepository filmeRepository;

	@RabbitListener(queues = MQConfig.QUEUE) //Alterar a fila de escuta para cada microserviço
    public void listener(CustomMessage message) {
		if (cacheService.fetchUserById(message.getMessage())!= null) {
			count = cacheService.fetchUserById(message.getMessage()).getVisto();
			count += 1;
		}else{
			count = 1;
		};
		Movie filme = filmeRepository.findById(message.getMessage());
		Cache cache = new Cache();
		cache.setId(message.getMessage());
		cache.setFilme(filme.getName());
		cache.setCategoria(filme.getCategoryName().getName());
		cache.setVisto(count);
		cacheService.saveUser(cache);
    }
}
