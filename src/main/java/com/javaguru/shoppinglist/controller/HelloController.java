package com.javaguru.shoppinglist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;
@Controller
@RequestMapping("/hello")
public class HelloController {


        @RequestMapping(method = RequestMethod.GET)
        public String printHello(ModelMap model) {
            model.addAttribute("message", "Hello Spring MVC Framework! - " +
                    new Random().nextInt(101));
            return "hello";
        }

    }

