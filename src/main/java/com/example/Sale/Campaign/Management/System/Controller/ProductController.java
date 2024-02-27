package com.example.Sale.Campaign.Management.System.Controller;

import com.example.Sale.Campaign.Management.System.Model.*;
import com.example.Sale.Campaign.Management.System.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductServices productServices;

    @PostMapping("saveproduct")
    public List<ProductData> saveProduct(@RequestBody List<ProductData> productData){
        return productServices.saveProduct(productData);
    }
    @GetMapping("getProductDetails")
    private Optional<ProductData> getProductDetails(@RequestParam String productid){
        return productServices.getProductDetails(productid);
    }
    @GetMapping("allProducts")
    private List<ProductData> allProducts(){
        return productServices.allProducts();
    }
    @GetMapping("getDataByPage")
    public List<ProductData> getData(@RequestBody PageData pageData){
        return productServices.getData(pageData);
    }

    @PostMapping("saveCampaign")
    public SaleCompaignDTO addCompaignData(@RequestBody SaleCompaignDTO saleCompaignDTO){
        return productServices.addCompaignData(saleCompaignDTO);
    }

    @Scheduled(cron = "0 55 17 ? * *")
    public void triggerDiscount(){
        productServices.triggerdiscount();
    }
    @Scheduled(cron = "0 56 17 ? * *")
    private void untriggerDiscount(){
        productServices.untriggerDiscount();
    }

    @GetMapping("get-history")
    private List<ProductPriceHistory> getHistory(@RequestParam String productid){
        return productServices.getHistory(productid);
    }

    @GetMapping("pastCampaign")
    private List<SaleCompaignData> pastCampaign(){
        return productServices.pastCampaign();
    }

    @GetMapping("currentCampaign")
    private List<SaleCompaignData> currentCampaign(){
        return productServices.currentCampaign();
    }

    @GetMapping("upcommingCampaign")
    private List<SaleCompaignData> upcommingCampaign(){
        return productServices.upcommingCampaign();
    }
}
