package com.netflix.apirest.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.apirest.entidades.Director;
import com.netflix.apirest.repositorios.DirectorRepository;

@Service
public class DirectorService {
    @Autowired
	private DirectorRepository directorRepository;
	
	public List<Director> getAll(){
		return directorRepository.findAll();
	}
	
	public Director getOne(Integer id) {
		return directorRepository.findById(id).orElse(null);
	}
	
	public Director save(Director director){
        return directorRepository.save(director);
    }
	
	public void deleteById(Integer id) {
		directorRepository.deleteById(id);
	}
}

