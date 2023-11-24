package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(name = "minDate", required = false) String minDate,
													  @RequestParam(name = "maxDate",required = false) String maxDate,
													  @RequestParam(name = "namePart") String namePart, Pageable pageable) {
		Page<SaleMinDTO> report = service.getReport(minDate, maxDate, namePart, pageable);
		return null;
	}

//	@GetMapping(value = "/summary")
//	public ResponseEntity<?> getSummary() {
//		// TODO
//		return null;
//	}
}
