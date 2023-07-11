package com.nogayhusrev.accounting.repository;

import com.nogayhusrev.accounting.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByTitle(String title);

}
