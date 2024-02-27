package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductData,String> {
}
