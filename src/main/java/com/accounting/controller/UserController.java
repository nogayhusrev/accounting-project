package com.accounting.controller;


import com.accounting.dto.CompanyDto;
import com.accounting.dto.UserDto;
import com.accounting.service.CompanyService;
import com.accounting.service.RoleService;
import com.accounting.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final CompanyService companyService;

    public UserController(UserService userService, RoleService roleService, CompanyService companyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("users",userService.findAll());

        return "/user/user-list";
    }


    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("newUser", new UserDto());
        model.addAttribute("userRoles", roleService.getRolesForCurrentUser());
        model.addAttribute("companies", companyService.getCompaniesForCurrentUser());

        return "/user/user-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult) {

        if (userService.isExist(userDto)) {
            bindingResult.rejectValue("title", " ", "This title already exists.");
        }


        if (bindingResult.hasErrors()) {
            return "user/user-create";
        }

        userService.save(userDto);

        return "redirect:/users/list";

    }

    @GetMapping("/update/{userId}")
    public String update(@PathVariable Long userId, Model model){

        model.addAttribute("user",userService.findById(userId));
        model.addAttribute("userRoles", roleService.getRolesForCurrentUser());
        model.addAttribute("companies", companyService.getCompaniesForCurrentUser());

        return "/user/user-update";

    }

    @PostMapping("/update/{userId}")
    public String update(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, @PathVariable Long userId, Model model) throws CloneNotSupportedException {


        if (bindingResult.hasErrors()) {
            userDto.setId(userId);
            return "redirect:/users/update/" + userId;
        }

        userService.update(userDto, userId);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId){
        userService.delete(userService.findById(userId));
        return "redirect:/users/list";
    }

}
