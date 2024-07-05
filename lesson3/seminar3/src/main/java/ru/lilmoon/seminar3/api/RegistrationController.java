package ru.lilmoon.seminar3.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@RestController
//@RequestMapping("/login")
public class RegistrationController {

    @RequestMapping
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout",required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign_in");
//        model.addAttribute("error",error != null);
//        model.addAttribute("logout",logout != null);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public String validateUser(){
        return "auth";
    }
}
