package com.nogayhusrev.accounting.repository;

import com.nogayhusrev.accounting.entity.Category;
import com.nogayhusrev.accounting.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCompany(Company company);
}
