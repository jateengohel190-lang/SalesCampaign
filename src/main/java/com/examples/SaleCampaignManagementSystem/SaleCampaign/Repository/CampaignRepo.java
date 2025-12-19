package com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepo extends JpaRepository<Campaign, Long> {
//    List<Campaign> findActiveCampaigns(LocalDate today);

    @Query("SELECT c FROM Campaign c LEFT JOIN FETCH c.campaignDiscounts WHERE :today BETWEEN c.startDate AND c.endDate")
    List<Campaign> findActiveCampaigns(@Param("today") LocalDate today);

//    List<Campaign> findExpiredCampaigns(LocalDate today);
//
    @Query("SELECT c FROM Campaign c LEFT JOIN FETCH c.campaignDiscounts WHERE c.endDate = :today")
    List<Campaign> findExpiredCampaigns(@Param("today") LocalDate today);



}
