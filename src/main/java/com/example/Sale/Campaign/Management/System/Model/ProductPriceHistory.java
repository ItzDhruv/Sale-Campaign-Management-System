package com.example.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@Table(name = "product_history")
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    private String productid;
    private String title;
    @Getter
    private long price;
    @Getter
    private long date;


    public ProductPriceHistory(String productId,String title, long currentPrice, long currentTimeMillis) {
        this.id = getId();
        this.productid = productId;
        this.title = title;
        this.price = currentPrice;
        this.date = currentTimeMillis;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
