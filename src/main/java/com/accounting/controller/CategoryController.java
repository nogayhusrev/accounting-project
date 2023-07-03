package com.accounting.controller;


import com.accounting.dto.CategoryDto;
import com.accounting.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("categories", categoryService.findAll());

        return "/category/category-list";
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("newCategory", new CategoryDto());


        return "/category/category-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newCategory") CategoryDto categoryDto, BindingResult bindingResult, Model model) {

        if (categoryService.isExist(categoryDto)) {
            bindingResult.rejectValue("description", " ", "This category already exists.");
        }


        if (bindingResult.hasErrors()) {

            model.addAttribute("newCategory", new CategoryDto());

            return "/category/category-create";
        }

        categoryService.save(categoryDto);

        return "redirect:/categories/list";

    }

    @GetMapping("/update/{categoryId}")
    public String update(@PathVariable("categoryId") Long categoryId, Model model) {

        model.addAttribute("category", categoryService.findById(categoryId));


        return "/category/category-update";

    }

    @PostMapping("/update/{categoryId}")
    public String update(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult bindingResult, @PathVariable Long categoryId, Model model) throws CloneNotSupportedException {

        if (categoryService.isExist(categoryDto, categoryId)) {
            bindingResult.rejectValue("description", " ", "This category already exists.");
        }

        if (bindingResult.hasErrors()) {

            return "redirect:/categories/update/" + categoryId;
        }

        categoryService.update(categoryDto, categoryId);
        return "redirect:/categories/list";
    }

    @GetMapping("/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/categories/list";
    }

}
