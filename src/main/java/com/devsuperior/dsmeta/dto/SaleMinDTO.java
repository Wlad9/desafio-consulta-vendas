package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projection.SaleMinProjection;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellerName;

	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
	}

	public SaleMinDTO(SaleMinProjection projection) {
		id = projection.getId();
		date = projection.getDate();
		amount = projection.getAmount();
		sellerName = projection.getSellerName();
	}

	// Novo construtor para atender à query
	public SaleMinDTO(Long id, LocalDate date, Double amount, String sellerName) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerName = sellerName;
	}
	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	@Override
	public String toString() {
		return "SaleMinDTO{" +
				"id=" + id +
				", amount=" + amount +
				", date=" + date +
				", sellerName='" + sellerName + '\'' +
				'}';
	}
}
