package com.netflix.apirest.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.apirest.entidades.Category;
import com.netflix.apirest.repositorios.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
	public Category getOne(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
	public Category save(Category category){
        return categoryRepository.save(category);
    }
	
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}
}
