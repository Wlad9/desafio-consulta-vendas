package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SumaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SumaryDTO>> getSummary(@RequestParam(name = "minDate", required = false) String minDate,
													  @RequestParam(name = "maxDate", required = false) String maxDate) {
		List<SumaryDTO> sumario = service.search2(minDate, maxDate);
		return ResponseEntity.status(HttpStatus.OK).body(sumario);
	}
	@GetMapping(value="/report")
	public ResponseEntity<Page<List<ReportDTO>>> getReport(
			@RequestParam(name = "minDate", required = false) String minDate,
			@RequestParam(name = "maxDate", required = false) String maxDate,
			@RequestParam(name = "name", required = false, defaultValue = "") String name, Pageable pageable){
		Page<List<ReportDTO>> result = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(result);
	}
}
