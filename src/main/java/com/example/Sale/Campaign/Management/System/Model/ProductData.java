package com.example.Sale.Campaign.Management.System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "product_data")
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {

    @Id
    String productId;
    String title;
    long mrp;
    long currentPrice;
    long discount;
    int inventory;

    public void setProductId() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "");
        this.productId= uuidString.substring(0, 5);
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }
}
