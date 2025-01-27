package com.enchereseni.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
//@RequestMapping("/encheres")
public class HomeController {


@GetMapping("/encheres")
    public String Home (Model model) {
return "index";

}


}
