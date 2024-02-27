package com.example.Sale.Campaign.Management.System.Model;

import java.util.List;


public class SaleCompaignDTO {

    private String title;
    private String startDate;
    private String endtDate;
    List<DiscountDetails> campaignDiscount;

    public String getStartDate() {
        return startDate;
    }

    public String getEndtDate() {
        return endtDate;
    }

    public String getTitle() {
        return title;
    }

    public List<DiscountDetails> getCampaignDiscount() {
        return campaignDiscount;
    }
}
