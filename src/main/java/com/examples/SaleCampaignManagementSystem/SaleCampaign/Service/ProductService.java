package com.examples.SaleCampaignManagementSystem.SaleCampaign.Service;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.DTO.CampaignRequest;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Campaign;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.CampaignDiscount;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Product;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.history;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository.CampaignRepo;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository.ProductRepo;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository.historyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private historyRepo historyRepo;

    @Autowired
    private CampaignRepo campaignRepo;

    public List<Product> addProduct(List<Product> product) {
        return productRepo.saveAll(product);
    }

    public Page<Product> getProductsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return productRepo.findAll(pageable);
    }

    public Campaign createCampaign(Campaign request)
    {
        if (request.getCampaignDiscounts() != null) {
            for (CampaignDiscount cd : request.getCampaignDiscounts()) {
                cd.setCampaign(request);  // REQUIRED !!
            }
        }
        return campaignRepo.save(request);
    }

    public void addProductHistory(List<Product> product)
    {
        for(Product p : product)
        {
            history his  = new history();
            his.setProduct_id(p.getProductId());
            his.setProduct_name(p.getTitle());
            his.setCurr_price(p.getCurrentPrice());
            LocalDate today = LocalDate.now();
            his.setDate(today);
            historyRepo.save(his);
        }
    }

    public List<history> getHistory(Long id) {
        return historyRepo.findAllById(id);
    }

//    public Campaign createCampaign(CampaignRequest request) {
//
//        Campaign campaign = new Campaign();
//        campaign.setStartDate(request.getStartDate());
//        campaign.setEndDate(request.getEndDate());
//        campaign.setTitle(request.getTitle());
//
//        List<CampaignDiscount> discounts = new ArrayList<>();
//
//        if (request.getCampaignDiscount() != null) {
//
//            for (CampaignRequest.CampaignDiscountItems item : request.getCampaignDiscount()) {
//
//                CampaignDiscount cd = new CampaignDiscount();
//
//                cd.setProductId(item.getProductId());
//                cd.setDiscount(item.getDiscount() != null ? item.getDiscount() : 0.0);
//                cd.setCampaign(campaign);
//
//                discounts.add(cd);
//            }
//        }
//
//        campaign.setCampaignDiscounts(discounts);
//
//        return campaignRepo.save(campaign);
//    }

}
