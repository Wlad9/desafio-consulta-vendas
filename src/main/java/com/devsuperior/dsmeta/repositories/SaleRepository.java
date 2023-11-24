package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.date, obj.amount, obj.seller.name)" +
            " FROM Sale obj" +
            " WHERE obj.date BETWEEN :dataMinima AND :dataMaxima" +
            " AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
    List<SaleMinDTO> searchReport(LocalDate dataMinima, LocalDate dataMaxima, String name);
}
