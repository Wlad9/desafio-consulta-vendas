package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReport(String dataInicial, String dataFinal, String namePart, Pageable pageable) {
		LocalDate dataMaxima = fomataDataMaxima(dataFinal);
		LocalDate dataMinima = formataDataMinima(dataInicial, dataMaxima);

		return repository.searchReport(dataMinima, dataMaxima, namePart, pageable);
	}

	private LocalDate fomataDataMaxima(String dataFinal) {
		if(dataFinal == null || dataFinal.isBlank()){
			return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		return LocalDate.parse(dataFinal);
	}

	private LocalDate formataDataMinima(String inicio, LocalDate fim) {
		if(inicio == null || inicio.isBlank()){
			return fim.minusYears(1L);
		}
		return LocalDate.parse(inicio);
	}

}
