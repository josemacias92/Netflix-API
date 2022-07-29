package com.netflix.apirest.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.apirest.entidades.Title;
import com.netflix.apirest.servicios.TitleService;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

	@Autowired
	TitleService titleService;
	
	@GetMapping
	public ResponseEntity<List<Title>> showAll(){
		List<Title> list = titleService.getAll();
		
			return  list.size() == 0 
					? ResponseEntity.noContent().build() 
					: ResponseEntity.ok().body(titleService.getAll());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Title> showOne(@PathVariable int id){
		
		
		Title item = titleService.getOne(id);
		
		return  item == null 
				? ResponseEntity.notFound().build() 
				: ResponseEntity.ok().body(titleService.getOne(id));
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable int id){
		
		 titleService.deletebyId(id);
		 return ResponseEntity.ok().build();
		 
	}
	@PostMapping
	public ResponseEntity<Title> save(@RequestBody Title title){
		return ResponseEntity.ok().body(titleService.save(title));
		
		  
	}
	@PutMapping
	public ResponseEntity<Title> edit(@RequestBody Title title){
		return ResponseEntity.ok().body(titleService.save(title));
		
	}
}
