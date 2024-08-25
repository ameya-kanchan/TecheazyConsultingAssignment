package com.ecommerce.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.server.model.Product;
import com.ecommerce.server.service.ProductService;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String greet(){
        return "Hello";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = productService.geProductById(id);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile)
    {
        try
        {
            Product tempProd = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(tempProd, HttpStatus.CREATED);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
        
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.geProductById(productId);
        byte[] imageFile =  product.getImageData();
        return  ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
        
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int productId, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product temProduct;
        try {
            temProduct = productService.updateProduct(productId, product, imageFile);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
        }
        if(temProduct != null) {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = productService.geProductById(id);
        if(product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not Found", HttpStatus.NOT_FOUND);
            
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

}
