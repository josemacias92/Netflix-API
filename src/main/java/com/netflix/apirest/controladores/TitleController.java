package com.netflix.apirest.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.apirest.entidades.Title;
import com.netflix.apirest.servicios.TitleService;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

	@Autowired
	TitleService titleService;

	@GetMapping
	public ResponseEntity<List<Title>> showAll() {
		List<Title> list = titleService.getAll();

		return list.size() == 0 ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(titleService.getAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Title> showOne(@PathVariable String id) {

		Title item = titleService.getOne(id);

		return item == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(titleService.getOne(id));

	}

	@GetMapping("/search/{tipo}")
	public ResponseEntity<List<Title>> showBy(@PathVariable String tipo, @RequestParam Integer id,
			@RequestParam(required = false) Integer top) {

		Pageable limit = top == null ? null : PageRequest.of(0, top);

		List<Title> list = null;

		switch (tipo) {
		case "category":
			list = titleService.getByCategory(id, limit);
			break;
		case "actor":
			list = titleService.getByActor(id, limit);
			break;
		case "director":
			list = titleService.getByDirector(id, limit);
			break;
		default:
			throw new IllegalStateException("Invalid search type: " + tipo);
		}

	return list.size() == 0
			? ResponseEntity.noContent().build()
			: ResponseEntity.ok().body(list);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Title> deleteOne(@PathVariable String id){
		
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
