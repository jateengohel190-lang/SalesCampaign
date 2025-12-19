package com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String title;

    private Double mrp;

    private Double currentPrice;

    private Double discount;

    private Integer inventoryCount;
}
