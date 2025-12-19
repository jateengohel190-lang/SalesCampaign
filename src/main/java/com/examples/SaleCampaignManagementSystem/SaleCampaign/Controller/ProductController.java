package com.examples.SaleCampaignManagementSystem.SaleCampaign.Controller;

import com.examples.SaleCampaignManagementSystem.SaleCampaign.DTO.CampaignRequest;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.DTO.ProductPageResponse;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Campaign;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.Product;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Entity.history;
import com.examples.SaleCampaignManagementSystem.SaleCampaign.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("AddProducts")
    public List<Product> addProduct(@RequestBody List<Product> product) {

        List<Product> products = productService.addProduct(product);

        productService.addProductHistory(product);

        return products;
    }

    @GetMapping("page")
    public ProductPageResponse getProductsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
    ) {
        Page<Product> productPage = productService.getProductsPage(page, size);

        ProductPageResponse response = new ProductPageResponse();

        response.setProducts(productPage.getContent());
        response.setPage(productPage.getNumber());
        response.setPageSize(productPage.getSize());
        response.setTotalPages(productPage.getTotalPages());

        return response;
    }

    @PostMapping("StartCampaign")
    public Campaign CreateCampaign(@RequestBody Campaign request){
        return productService.createCampaign(request);
    }

    @GetMapping("get-History/{id}")
    public List<history> getHistory(@PathVariable Long id){
        return productService.getHistory(id);
    }



}
