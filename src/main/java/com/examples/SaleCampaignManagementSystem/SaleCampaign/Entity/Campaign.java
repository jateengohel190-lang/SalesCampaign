package com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@JsonPropertyOrder({ "startDate", "endDate", "title", "CampaignId", "campaignDiscounts" })
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CampaignId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String title;

    private boolean applied = false;

    @OneToMany(mappedBy = "campaign",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CampaignDiscount> campaignDiscounts;

}
