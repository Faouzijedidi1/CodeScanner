package com.jedidi.ssd.codescan.controller;

/*
* Code Made by Faouzi Jedidi
* S1719017
*
*/


import com.jedidi.ssd.codescan.repository.UserRepository;
import com.jedidi.ssd.codescan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jedidi.ssd.codescan.model.User;

import javax.validation.Valid;

@Controller
public class AuthenticationController {


    @Autowired
    UserService userService;

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
         User user = new User();
         modelAndView.addObject("user", user);
         modelAndView.setViewName("register"); // resources/template/register.html
         return modelAndView;
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("admin"); // resources/template/admin.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if (bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please Correct the errors in the form");
            modelMap.addAttribute("bindingResult",bindingResult);
        }

        else if (userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "User Already Exists!");
        }
        // Save user if no binding errors
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User is Successfully Registered!");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;


    }

    // Attempt to get username to display
//    @Autowired
//    UserRepository user1;
//    @GetMapping
//    public String currentUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
//
//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String email = loggedInUser.getName();
//
//        User user2 = user1.findByEmail(email);
//        String firstname = user2.getName();
//        model.addAttribute("firstName", firstname);
//        model.addAttribute("emailAddress", email);
//
//        return "userProfile1"; //this is the name of my template
//    }
}
