package com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class history
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long history_id;

    @Column
    private Long product_id;

    @Column
    private String product_name;

    @Column
    private double curr_price ;

    @Column
    private LocalDate date;

    @Column
    private String campaign_name ;

    @PrePersist
    public void adddefault()
    {
        if(this.campaign_name == null)
        {
            this.campaign_name = " no campaign";
        }
    }


}
