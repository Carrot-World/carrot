package com.carrot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/page/main")
    public String main2() {
        return "index";
    }
}
