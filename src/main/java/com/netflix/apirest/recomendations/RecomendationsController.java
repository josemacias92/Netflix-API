package com.netflix.apirest.recomendations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.apirest.entidades.Title;
import com.netflix.apirest.servicios.CategoryService;
import com.netflix.apirest.servicios.TitleService;

@RestController
@RequestMapping("/api/recommend")
public class RecomendationsController {

	@Autowired
	TitleService titleService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/best")
	public ResponseEntity<List<Title>> getBest(@RequestParam(required = false) Integer top) {

		Pageable limit = top == null 
				? null  
				: PageRequest.of(0, top);
		
		List<Title> list = titleService.getBest(limit);

		return list.size() == 0 
				? ResponseEntity.noContent().build() 
				: ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Title>> BestByCategory(@PathVariable int categoryId, @RequestParam(required = false) Integer top) {

		Pageable limit = top == null 
				? null  
				: PageRequest.of(0, top);
		
		List<Title> lista = titleService.getBestByCategory(categoryId, limit);


		return lista.size() == 0 
				? ResponseEntity.noContent().build() 
				: ResponseEntity.ok().body(lista);
	}
}
