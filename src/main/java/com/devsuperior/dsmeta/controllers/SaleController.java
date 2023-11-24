package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

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
	public ResponseEntity<List<SaleMinDTO>> search1(@RequestParam(name = "minDate", required = false) String minDate,
													@RequestParam(name = "maxDate", required = false) String maxDate,
													@RequestParam(name = "name", required = false, defaultValue = "") String name){
		System.out.println(minDate+"\t"+maxDate+"\tnome="+ name);
		List<SaleMinDTO> report = service.getReport(minDate, maxDate, name);
		for(SaleMinDTO dto: report){
			System.out.println(dto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(report);
	}


//	@GetMapping(value = "/summary")
//	public ResponseEntity<?> getSummary() {
//		// TODO
//		return null;
//	}
}
