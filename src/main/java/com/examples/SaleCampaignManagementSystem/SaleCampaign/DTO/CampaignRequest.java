package com.examples.SaleCampaignManagementSystem.SaleCampaign.DTO;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.CampaignDiscount;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class CampaignRequest {
    private String startDate;
    private String endDate;
    private String title;

    private List<CampaignDiscountItems> campaignDiscount;

    @Data
    public static class CampaignDiscountItems{
            private Long productId;
            private Double discount;
    }
}
