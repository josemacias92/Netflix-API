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

import com.netflix.apirest.entidades.Category;
import com.netflix.apirest.servicios.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
	private CategoryService categoryService;

	@GetMapping()
	public ResponseEntity<List<Category>> showAll(){
		
		List<Category> categories = categoryService.getAll();
		
		if(categories.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Category> edit(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable Integer id) {
		categoryService.deleteById(id);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> showOne(@PathVariable Integer id){
		
		Category category = categoryService.getOne(id);
		
		if(category == null) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
}
