package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.DiscountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDetailsRepository extends JpaRepository<DiscountDetails,Integer> {
}
