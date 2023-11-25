package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SumaryDTO;
import com.devsuperior.dsmeta.projection.SumaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<SaleMinDTO> getReport(String dataInicial, String dataFinal, String nome) {
        LocalDate dataMaxima = fomataDataMaxima(dataFinal);
        LocalDate dataMinima = formataDataMinima(dataInicial, dataMaxima);
        return repository.searchReport(dataMinima, dataMaxima, nome);
    }

    public List<SumaryDTO> search2(String dataInicial, String dataFinal) {
        LocalDate dataMaxima = fomataDataMaxima(dataFinal);
        LocalDate dataMinima = formataDataMinima(dataInicial, dataMaxima);
        System.out.println("DATA FORMATADA:"+dataMinima+"\t"+dataMaxima);
        List<SumaryProjection> lista = repository.searchSummary(dataMinima, dataMaxima);
        System.out.println("tamanho:"+lista.size());
        for(SumaryProjection proj: lista){
            System.out.println("Nome:"+proj.getSellerName()+"\t Total:"+proj.getTotal());
        }
        List<SumaryDTO> lista2 = lista.stream().map(x -> new SumaryDTO(x)).collect(Collectors.toList());
        return lista2;
    }

    private LocalDate fomataDataMaxima(String dataFinal) {
        if (dataFinal == null || dataFinal.isBlank()) {
            return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        }
        return LocalDate.parse(dataFinal);
    }

    private LocalDate formataDataMinima(String inicio, LocalDate fim) {
        if (inicio == null || inicio.isBlank()) {
            return fim.minusYears(1L);
        }
        return LocalDate.parse(inicio);
    }


}
