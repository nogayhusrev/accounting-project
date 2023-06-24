package com.accounting.controller;


import com.accounting.dto.CategoryDto;
import com.accounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("products",productService.findAll());

        return "/product/product-list";
    }


//    @GetMapping("/create")
//    public String create(Model model){
//
//        model.addAttribute("newCategory", new CategoryDto());
//
//
//        return "/category/category-create";
//
//    }
//
//    @PostMapping("/create")
//    public String create(@Valid @ModelAttribute("newCategory") CategoryDto categoryDto, BindingResult bindingResult, Model model) {
//
//        if (categoryService.isExist(categoryDto)) {
//            bindingResult.rejectValue("description", " ", "This category already exists.");
//        }
//
//
//        if (bindingResult.hasErrors()) {
//
//            return "/category/category-create";
//        }
//
//        categoryService.save(categoryDto);
//
//        return "redirect:/categories/list";
//
//    }
//
//    @GetMapping("/update/{categoryId}")
//    public String update(@PathVariable("categoryId") Long categoryId, Model model){
//
//        model.addAttribute("category",categoryService.findById(categoryId));
//
//
//        return "/category/category-update";
//
//    }
//
//    @PostMapping("/update/{categoryId}")
//    public String update(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult bindingResult, @PathVariable Long categoryId, Model model) throws CloneNotSupportedException {
//
//
//        if (bindingResult.hasErrors()) {
//
//            return "redirect:/categories/update/" + categoryId;
//        }
//
//        categoryService.update(categoryDto, categoryId);
//        return "redirect:/categories/list";
//    }
//
//    @GetMapping("/delete/{categoryId}")
//    public String delete(@PathVariable("categoryId") Long categoryId){
//        categoryService.delete(categoryService.findById(categoryId));
//        return "redirect:/categories/list";
//    }

}
