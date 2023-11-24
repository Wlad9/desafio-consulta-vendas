package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SmryProjection;

public class SmryDTO {
    private String sellerName;
    private Double total;

    public SmryDTO() {
    }

    public SmryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SmryDTO(SmryProjection proj) {
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
