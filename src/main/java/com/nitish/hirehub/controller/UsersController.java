package com.nitish.hirehub.controller;

import com.nitish.hirehub.entity.Users;
import com.nitish.hirehub.entity.UsersType;
import com.nitish.hirehub.service.UsersTypeService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService) {
        this.usersTypeService = usersTypeService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> userTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", userTypes);
        model.addAttribute("user", new Users());
        return "register";
    }
}
