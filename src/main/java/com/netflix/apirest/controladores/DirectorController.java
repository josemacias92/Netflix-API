package com.netflix.apirest.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.apirest.entidades.Director;
import com.netflix.apirest.servicios.DirectorService;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {
    @Autowired
	private DirectorService directorService;

	@GetMapping()
	public ResponseEntity<List<Director>> showAll(){
		
		List<Director> directors = directorService.getAll();
		
		if(directors.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return new ResponseEntity<>(directors, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Director> save(@RequestBody Director director) {
		return new ResponseEntity<>(directorService.save(director), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Director> edit(@RequestBody Director director) {
		return new ResponseEntity<>(directorService.save(director), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable Integer id) {
		directorService.deleteById(id);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Director> showOne(@PathVariable Integer id){
		
		Director director = directorService.getOne(id);
		
		if(director == null) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<>(director, HttpStatus.OK);
	}
}
