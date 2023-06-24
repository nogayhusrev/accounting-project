package com.accounting.service.impl;

import com.accounting.dto.ProductDto;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.ProductRepository;
import com.accounting.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public ProductDto findById(Long aLong) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(product -> mapperUtil.convert(product,new ProductDto())).collect(Collectors.toList());
    }

    @Override
    public void save(ProductDto productDto) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }

    @Override
    public void delete(ProductDto productDto) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }

    @Override
    public void update(ProductDto productDto, Long aLong) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }

    @Override
    public boolean isExist(ProductDto productDto) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }
}
