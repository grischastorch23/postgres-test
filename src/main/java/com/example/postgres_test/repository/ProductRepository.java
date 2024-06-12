package com.example.postgres_test.repository;

import java.util.List;

import com.example.postgres_test.model.ProductTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductTest, Long> {

}
