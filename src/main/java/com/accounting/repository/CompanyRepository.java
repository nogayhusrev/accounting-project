package com.accounting.repository;

import com.accounting.entity.Company;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

public interface CompanyRepository extends JpaAttributeConverter<Company, Long> {


}
