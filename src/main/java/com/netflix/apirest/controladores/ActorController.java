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

import com.netflix.apirest.entidades.Actor;
import com.netflix.apirest.servicios.ActorService;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
	private ActorService actorService;

	@GetMapping()
	public ResponseEntity<List<Actor>> showAll(){
		
		List<Actor> actors = actorService.getAll();
		
		if(actors.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Actor> save(@RequestBody Actor actor) {
		return new ResponseEntity<>(actorService.save(actor), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Actor> edit(@RequestBody Actor actor) {
		return new ResponseEntity<>(actorService.save(actor), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable Integer id) {
		actorService.deleteById(id);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Actor> showOne(@PathVariable Integer id){
		
		Actor actor = actorService.getOne(id);
		
		if(actor == null) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<>(actor, HttpStatus.OK);
	}
}
