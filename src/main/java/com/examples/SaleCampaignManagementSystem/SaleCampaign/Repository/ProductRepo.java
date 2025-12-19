package com.examples.SaleCampaignManagementSystem.SaleCampaign.Repository;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Product;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
