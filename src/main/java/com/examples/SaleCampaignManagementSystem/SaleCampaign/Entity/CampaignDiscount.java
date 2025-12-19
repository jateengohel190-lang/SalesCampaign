package com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class CampaignDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long productId;

    private double discount;

    @ManyToOne
    @JoinColumn(name = "Campaign_id")
    @JsonBackReference
    private Campaign campaign;

}
