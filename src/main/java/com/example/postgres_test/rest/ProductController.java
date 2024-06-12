package com.example.postgres_test.rest;


import com.example.postgres_test.model.ProductTest;
import com.example.postgres_test.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ProductTest>> getAllProducts() {
        try {

            List<ProductTest> products = new ArrayList<ProductTest>(productRepository.findAll());


            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ProductTest> createTutorial(@RequestBody ProductTest product) {
        try {
            ProductTest p2 = new ProductTest(2,"stein", "es ist ein stein", "123", "rund", 4, "http");
            LOGGER.info(String.format("Received Product -> %s", product.toString()));
            LOGGER.info(String.format("Real Product -> %s", p2.toString()));
            ProductTest _tutorial = productRepository
                    .save(new ProductTest(1, product.getName(), product.getDescription(), product.getPrice(), product.getDetails(),
                            product.getCount(), product.getImageLink()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
