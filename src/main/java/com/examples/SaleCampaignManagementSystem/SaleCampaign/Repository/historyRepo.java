package com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface historyRepo extends JpaRepository<history,Long>
{

    @Query(value = "select * from history where product_id =?1",nativeQuery = true)
    List<history> findAllById(Long id);
}
