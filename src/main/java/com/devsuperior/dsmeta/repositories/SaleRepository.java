package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projection.SumaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.date, obj.amount, obj.seller.name AS sellerName)" +
            " FROM Sale obj" +
            " WHERE obj.date BETWEEN :dataMinima AND :dataMaxima" +
            " AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
    List<SaleMinDTO> searchReport(LocalDate dataMinima, LocalDate dataMaxima, String name);

    @Query(nativeQuery = true, value = "SELECT tb_seller.name AS sellerName, SUM(tb_sales.amount) AS total " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller " +
            "ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :minima AND :maxima " +
            "GROUP BY tb_seller.name")
    List<SumaryProjection> searchSummary(LocalDate minima, LocalDate maxima);
}


//Consulta realizada no H2 funcionou
    /*
SELECT tb_seller.name, SUM(tb_sales.amount)
        FROM tb_sales
        INNER JOIN  tb_seller
        ON tb_sales.seller_id = tb_seller.id
        WHERE tb_sales.date BETWEEN '2022-01-01' AND '2023-10-31'
        GROUP BY tb_seller.name
     */