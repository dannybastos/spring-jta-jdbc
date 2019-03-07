package com.example.spring.jta.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jta.jdbc.service.SellService;

@RestController
public class SellController {

	private SellService sellService;
	
	@Autowired
	public SellController(SellService sellService) {
		this.sellService = sellService;
	}
	
	@PostMapping(path="/sell", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> performTransf(@RequestParam("product_id") Long productId, 
			@RequestParam("qty") Long qty) {
		ResponseEntity<String> result;
		try {
			sellService.sell(productId, qty);
			result = ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			result = ResponseEntity.status(500).body(e.getMessage());
		}		
		return result;
	}
	
}
