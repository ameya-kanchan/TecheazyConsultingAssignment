package com.ecommerce.server.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.server.model.Product;
import com.ecommerce.server.repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        
        return productRepo.findAll();
    }

    public Product geProductById(int prodId) {
        return productRepo.findById(prodId).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException{
       product.setImageName(imageFile.getOriginalFilename());
       product.setImageType(imageFile.getContentType());
       product.setImageData(imageFile.getBytes());
       return productRepo.save(product);
    }

    public Product updateProduct(int productId, Product product, MultipartFile imageFile) throws IOException {
       product.setImageData(imageFile.getBytes());
       product.setImageName(imageFile.getOriginalFilename());
       product.setImageType(imageFile.getContentType());
        return productRepo.save(product);
    }

    public void deleteProduct(int prodId) {
        productRepo.deleteById(prodId);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
