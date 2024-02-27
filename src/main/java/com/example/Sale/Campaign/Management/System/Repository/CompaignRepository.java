package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.SaleCompaignData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaignRepository extends JpaRepository<SaleCompaignData,Integer> {
}
