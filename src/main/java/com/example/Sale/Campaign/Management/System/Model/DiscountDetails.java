package com.example.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "discount_details")
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String productId;
    private int saleId;
    private long discount;
    private long startDate;
    private long endDate;
    @Getter
    private boolean issale;

    public void setIssale(boolean issale) {
        this.issale = issale;
    }

    public DiscountDetails(String productId,int saleId, long discount, long startDate, long endDate) {
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.issale = false;
    }
}
