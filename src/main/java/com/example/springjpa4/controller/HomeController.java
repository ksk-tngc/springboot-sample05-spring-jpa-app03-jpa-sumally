package com.example.springjpa4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * トップページのコントローラ。
 */
@Controller
public class HomeController {

    /**
     * トップページ。
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

}
