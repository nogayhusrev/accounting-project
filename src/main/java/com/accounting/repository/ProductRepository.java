package com.accounting.repository;

import com.accounting.dto.ProductDto;
import com.accounting.entity.Category;
import com.accounting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> findAllByCategory_Company();

}