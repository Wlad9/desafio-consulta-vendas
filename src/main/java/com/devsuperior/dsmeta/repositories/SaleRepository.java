package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projection.SmryProjection;
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

    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(tb_sales.amount) " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller " +
            "ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :minima AND :maxima " +
            "GROUP BY tb_seller.name")
    List<SmryProjection> searchSummary(LocalDate minima, LocalDate maxima);
}
