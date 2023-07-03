package com.accounting.repository;

import com.accounting.entity.Category;
import com.accounting.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCompany(Company company);
}
