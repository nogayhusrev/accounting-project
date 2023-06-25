package com.accounting.service.impl;

import com.accounting.dto.ProductDto;
import com.accounting.entity.Category;
import com.accounting.entity.Company;
import com.accounting.entity.Product;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.ProductRepository;
import com.accounting.service.ProductService;
import com.accounting.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }


    @Override
    public ProductDto findById(Long productId) {
        return mapperUtil.convert(productRepository.findById(productId).get(), new ProductDto());
    }

    @Override
    public List<ProductDto> findAll() {
        Company company = mapperUtil.convert(securityService.getCurrentUser().getCompany(), new Company());
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().getCompany().getTitle().equalsIgnoreCase(company.getTitle()))
                .sorted(Comparator.comparing((Product product) -> product.getCategory().getDescription())
                        .thenComparing(Product::getName))
                .map(product -> mapperUtil.convert(product,new ProductDto())).collect(Collectors.toList());
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = mapperUtil.convert(productDto,new Product());
        productRepository.save(product);

    }

    @Override
    public void delete(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).get();

        product.setIsDeleted(true);
        product.setName(product.getName() + " " + product.getId() + " DELETED");

        productRepository.save(product);
    }

    @Override
    public void update(ProductDto productDto, Long productId) {
        productDto.setId(productId);
        save(productDto);
    }

    @Override
    public boolean isExist(ProductDto productDto) {
        return findAll().stream().filter(savedProduct -> savedProduct.getName().equalsIgnoreCase(productDto.getName())).count() > 0;
    }
}
