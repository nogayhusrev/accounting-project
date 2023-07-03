package com.accounting.controller;


import com.accounting.dto.ProductDto;
import com.accounting.enums.ProductUnit;
import com.accounting.service.CategoryService;
import com.accounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("products", productService.findAll());

        return "/product/product-list";
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("newProduct", new ProductDto());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("productUnits", new ArrayList<>(Arrays.asList(
                ProductUnit.KG,
                ProductUnit.LBS,
                ProductUnit.PCS,
                ProductUnit.FEET,
                ProductUnit.INCH,
                ProductUnit.GALLON,
                ProductUnit.METER
        )));


        return "/product/product-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newProduct") ProductDto productDto, BindingResult bindingResult, Model model) {

        if (productService.isExist(productDto)) {
            bindingResult.rejectValue("name", " ", "This product already exists.");
        }


        if (bindingResult.hasErrors()) {

            model.addAttribute("newProduct", new ProductDto());
            model.addAttribute("categories", categoryService.findAll());

            model.addAttribute("productUnits", new ArrayList<>(Arrays.asList(
                    ProductUnit.KG,
                    ProductUnit.LBS,
                    ProductUnit.PCS,
                    ProductUnit.FEET,
                    ProductUnit.INCH,
                    ProductUnit.GALLON,
                    ProductUnit.METER
            )));

            return "/product/product-create";
        }

        productService.save(productDto);

        return "redirect:/products/list";

    }

    @GetMapping("/update/{productId}")
    public String update(@PathVariable("productId") Long productId, Model model) {

        model.addAttribute("product", productService.findById(productId));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("productUnits", new ArrayList<>(Arrays.asList(
                ProductUnit.KG,
                ProductUnit.LBS,
                ProductUnit.PCS,
                ProductUnit.FEET,
                ProductUnit.INCH,
                ProductUnit.GALLON,
                ProductUnit.METER
        )));


        return "/product/product-update";

    }

    @PostMapping("/update/{productId}")
    public String update(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult bindingResult, @PathVariable Long productId, Model model) {

        if (productService.isExist(productDto, productId)) {
            bindingResult.rejectValue("name", " ", "This category already exists.");
        }

        if (bindingResult.hasErrors()) {

            return "redirect:/products/update/" + productId;
        }

        productService.update(productDto, productId);
        return "redirect:/products/list";
    }

    @GetMapping("/delete/{productId}")
    public String delete(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return "redirect:/products/list";
    }


}
