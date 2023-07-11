package com.nogayhusrev.accounting.repository;

import com.nogayhusrev.accounting.entity.ClientVendor;
import com.nogayhusrev.accounting.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {
    List<ClientVendor> findAllByCompany(Company company);
}
