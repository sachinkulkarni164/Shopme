package com.product;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<Product> list(){
		return service.listAll();
	}
	
	@GetMapping("/productsid/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		try {
		Product product = service.get(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PostMapping("/productsave")
	public void add(@RequestBody Product product) {
		service.save(product);
		
	}
	
	@PutMapping("/productsupdate/{id}")
	public ResponseEntity<?> update(@RequestBody Product product,
		@PathVariable Integer id) {
		try {
		Product existProduct = service.get(id);
		service.save(product);
		return new ResponseEntity<>(service.get(id),HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/productsdelete/{id}")
	public void delete (@PathVariable Integer id) {
		service.delete(id);
		
	}

}
