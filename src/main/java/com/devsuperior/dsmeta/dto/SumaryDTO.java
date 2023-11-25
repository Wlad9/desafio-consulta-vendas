package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SumaryProjection;

public class SumaryDTO {
    private String sellerName;
    private Double total;

    public SumaryDTO() {
    }

    public SumaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SumaryDTO(SumaryProjection proj) {
        sellerName = proj.getSellerName();
        total = proj.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
