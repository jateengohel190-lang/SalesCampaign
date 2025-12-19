package com.examples.SaleCampaignManagementSystem.SaleCampaign.DTO;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({ "products", "page", "pageSize", "totalPages" })
public class ProductPageResponse {
    private List<Product> products;
    private int page;
    private int pageSize;
    private int totalPages;
}
