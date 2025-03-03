package com.nitish.hirehub.controller;

import com.nitish.hirehub.entity.Users;
import com.nitish.hirehub.entity.UsersType;
import com.nitish.hirehub.service.UsersService;
import com.nitish.hirehub.service.UsersTypeService;
import jakarta.validation.Valid;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> userTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", userTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model){
//        System.out.println("Users :: " + users);

        Optional<Users> optionalUser = usersService.getUserByEmail(users.getEmail());

        if(optionalUser.isPresent()){
            model.addAttribute("error", "Email already registered, try to login or register with new email");
            List<UsersType> userTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", userTypes);
            model.addAttribute("user", new Users());
            return "register";
        }

        usersService.addNew(users);
        return "dashboard";
    }
}
