package com.examples.SaleCampaignManagementSystem.SaleCampaign.Service;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Campaign;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.CampaignDiscount;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Product;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.history;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository.CampaignRepo;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository.ProductRepo;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository.historyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CampaignScheduler {

    @Autowired
    private CampaignRepo campaignRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private historyRepo historyRepo;

    // Runs every 1 minute → Apply discounts when campaign starts
    @Scheduled(fixedRate = 60000)
    public void applyCampaignDiscounts() {

        LocalDate today = LocalDate.now();

        List<Campaign> activeCampaigns = campaignRepo.findActiveCampaigns(today);


        for (Campaign campaign : activeCampaigns) {
            if(!campaign.isApplied())
            {
                for (CampaignDiscount cd : campaign.getCampaignDiscounts()) {

                    productRepo.findById(cd.getProductId()).ifPresent(product -> {
                        double discountedPrice = product.getCurrentPrice() -
                                (product.getMrp() * cd.getDiscount() / 100);

                        double dis = cd.getDiscount() + product.getDiscount();

                        product.setCurrentPrice(discountedPrice);
                        product.setDiscount(dis);

                        productRepo.save(product);

                        history his  = new history();
                        his.setProduct_id(product.getProductId());
                        his.setProduct_name(product.getTitle());
                        his.setCurr_price(product.getCurrentPrice());
                        his.setDate(campaign.getStartDate());
                        his.setCampaign_name(campaign.getTitle());
                        historyRepo.save(his);

                        System.out.println("start method is in running state");
                    });
                }
                campaign.setApplied(true);
                campaignRepo.save(campaign);
            }
        }
    }

    @Scheduled(cron = "0 03 10 * * *")
    public void removeExpiredCampaignDiscounts() {

        LocalDate today = LocalDate.now();

        List<Campaign> expiredCampaigns = campaignRepo.findExpiredCampaigns(today);

        for (Campaign campaign : expiredCampaigns) {
            for (CampaignDiscount cd : campaign.getCampaignDiscounts()) {

                productRepo.findById(cd.getProductId()).ifPresent(product -> {

                    double discount =product.getDiscount()- cd.getDiscount();
                    double amount =product.getCurrentPrice() +(product.getMrp() * cd.getDiscount() / 100);

                    product.setDiscount(discount);
                    product.setCurrentPrice(amount);

                    productRepo.save(product);

                    history his  = new history();
                    his.setProduct_id(product.getProductId());
                    his.setProduct_name(product.getTitle());
                    his.setCurr_price(product.getCurrentPrice());
                    his.setDate(campaign.getEndDate());
                    his.setCampaign_name(campaign.getTitle());
                    historyRepo.save(his);
                    System.out.println("end method is in running state");
                });
            }
        }
    }
}

