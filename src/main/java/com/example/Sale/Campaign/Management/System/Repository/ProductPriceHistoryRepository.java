package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.ProductPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory,Integer> {
    List<ProductPriceHistory> findAllByProductid(String productid);
}
