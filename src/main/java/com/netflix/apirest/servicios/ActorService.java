package com.netflix.apirest.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.apirest.entidades.Actor;
import com.netflix.apirest.repositorios.ActorRepository;

@Service
public class ActorService {
    @Autowired
	private ActorRepository actorRepository;
	
	public List<Actor> getAll(){
		return actorRepository.findAll();
	}
	
	public Actor getOne(Integer id) {
		return actorRepository.findById(id).orElse(null);
	}
	
	public Actor save(Actor actor){
        return actorRepository.save(actor);
    }
	
	public void deleteById(Integer id) {
		actorRepository.deleteById(id);
	}
	
}
