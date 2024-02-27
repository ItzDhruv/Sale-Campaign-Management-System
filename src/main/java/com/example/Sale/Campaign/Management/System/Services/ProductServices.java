package com.example.Sale.Campaign.Management.System.Services;

import com.example.Sale.Campaign.Management.System.Model.*;
import com.example.Sale.Campaign.Management.System.Repository.CompaignRepository;
import com.example.Sale.Campaign.Management.System.Repository.DiscountDetailsRepository;
import com.example.Sale.Campaign.Management.System.Repository.ProductPriceHistoryRepository;
import com.example.Sale.Campaign.Management.System.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CompaignRepository compaignRepository;

    @Autowired
    DiscountDetailsRepository discountDetailsRepository;

    @Autowired
    ProductPriceHistoryRepository productPriceHistoryRepository;

    public List<ProductData> saveProduct(List<ProductData> productData) {
        long currentTimeMillis = System.currentTimeMillis();
        for(ProductData productData1 : productData){
            productData1.setProductId();
            long discount = ((productData1.getMrp() - productData1.getCurrentPrice()) * 100) / productData1.getMrp();
            productData1.setDiscount(discount);
            productRepository.save(productData1);
            ProductPriceHistory productPriceHistory = new ProductPriceHistory(productData1.getProductId(), productData1.getTitle(), productData1.getCurrentPrice(),currentTimeMillis);
            productPriceHistoryRepository.save(productPriceHistory);
        }
        return productData;
    }


    public List<ProductData> getData(PageData pageData) {
        Pageable pageable = PageRequest.of(pageData.getPage(), pageData.getPageSize());
        Page<ProductData> pagedata = productRepository.findAll(pageable);
        List<ProductData> returnData = pagedata.getContent();
        return returnData;

    }
    public SaleCompaignDTO addCompaignData(SaleCompaignDTO saleCompaignDTO) {
        SaleCompaignData saleCompaignData = new SaleCompaignData(saleCompaignDTO.getTitle(),saleCompaignDTO.getStartDate(),saleCompaignDTO.getEndtDate());
        compaignRepository.save(saleCompaignData);

        for (DiscountDetails discountDetails : saleCompaignDTO.getCampaignDiscount()){
            DiscountDetails discountDetails1 = new DiscountDetails(discountDetails.getProductId(), saleCompaignData.getId(), discountDetails.getDiscount(),saleCompaignData.getStartDate(),saleCompaignData.getEndDate());
            discountDetailsRepository.save(discountDetails1);

        }

//        for (DiscountDetails discountDetails3 : saleCompaignDTO.getCampaignDiscount()){
//            ProductData productData = productRepository.findById(discountDetails.getProductId()).get();
//            long newPrice = productData.getCurrentPrice() - ((discountDetails.getDiscount()*productData.getCurrentPrice())/100);
//            productData.setCurrentPrice(newPrice);
//            long discount = ((productData.getMrp() - productData.getCurrentPrice()) * 100) / productData.getMrp();
//            productData.setDiscount(discount);
//            productRepository.save(productData);
//        }
        return saleCompaignDTO;
    }

    public void triggerdiscount() {
        List<DiscountDetails> discountDetails = discountDetailsRepository.findAll();
        long currentTimeMillis = System.currentTimeMillis();

        for (DiscountDetails discountDetails1 : discountDetails){
            if (currentTimeMillis > discountDetails1.getStartDate() && currentTimeMillis < discountDetails1.getEndDate() && !discountDetails1.isIssale()){
                discountDetails1.setIssale(true);
                discountDetailsRepository.save(discountDetails1);
                ProductData productData = productRepository.findById(discountDetails1.getProductId()).get();
                long newPrice = productData.getCurrentPrice() - ((discountDetails1.getDiscount()*productData.getCurrentPrice())/100);
                productData.setCurrentPrice(newPrice);
                long discount = ((productData.getMrp() - productData.getCurrentPrice())*100) / productData.getMrp();
                productData.setDiscount(discount);
                productRepository.save(productData);
                ProductPriceHistory productPriceHistory = new ProductPriceHistory(productData.getProductId(), productData.getTitle(), newPrice,currentTimeMillis);
                productPriceHistoryRepository.save(productPriceHistory);
            }
        }
    }

    public void untriggerDiscount() {
        List<DiscountDetails> discountDetails = discountDetailsRepository.findAll();
        long currentTimeMillis = System.currentTimeMillis();
        for (DiscountDetails discountDetails1 : discountDetails){
//            currentTimeMillis > discountDetails1.getEndDate() &&
            if (currentTimeMillis > discountDetails1.getEndDate() && discountDetails1.isIssale()){
                discountDetails1.setIssale(false);
                discountDetailsRepository.save(discountDetails1);
                ProductData productData = productRepository.findById(discountDetails1.getProductId()).get();
                List<ProductPriceHistory> productPriceHistories = getHistory(discountDetails1.getProductId());
                long setBackPrice = productPriceHistories.get(productPriceHistories.size()-2).getPrice();
                productData.setCurrentPrice(setBackPrice);
                long discount = ((productData.getMrp() - productData.getCurrentPrice()) * 100) / productData.getMrp();
                productData.setDiscount(discount);
                productRepository.save(productData);
                ProductPriceHistory productPriceHistory = new ProductPriceHistory(productData.getProductId(), productData.getTitle(), productData.getCurrentPrice(), currentTimeMillis);
                productPriceHistoryRepository.save(productPriceHistory);
            }
        }
    }

    public List<ProductPriceHistory> getHistory(String productid) {
        return productPriceHistoryRepository.findAllByProductid(productid);
    }

    public List<SaleCompaignData> pastCampaign() {
        List<SaleCompaignData> pastCampaign = new ArrayList<>();
        long currentTimeMillis = System.currentTimeMillis();
        for (SaleCompaignData saleCompaignData : compaignRepository.findAll()){
            if (currentTimeMillis > saleCompaignData.getEndDate()){
                pastCampaign.add(saleCompaignData);
            }
        }
        return pastCampaign;
    }

    public List<SaleCompaignData> currentCampaign() {
        List<SaleCompaignData> currentCampaign = new ArrayList<>();
        long currentTimeMillis = System.currentTimeMillis();
        for (SaleCompaignData saleCompaignData : compaignRepository.findAll()){
            if (currentTimeMillis > saleCompaignData.getStartDate() && currentTimeMillis < saleCompaignData.getEndDate()){
                currentCampaign.add(saleCompaignData);
            }
        }
        return currentCampaign;
    }

    public List<SaleCompaignData> upcommingCampaign() {
        List<SaleCompaignData> upcommingCampaign = new ArrayList<>();
        long currentTimeMillis = System.currentTimeMillis();
        for (SaleCompaignData saleCompaignData : compaignRepository.findAll()){
            if (currentTimeMillis < saleCompaignData.getStartDate()){
                upcommingCampaign.add(saleCompaignData);
            }
        }
        return upcommingCampaign;
    }

    public List<ProductData> allProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductData> getProductDetails(String productid) {
        return productRepository.findById(productid);
    }
}
