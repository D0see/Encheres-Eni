package com.enchereseni.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RequestMapping("/encheres")
public class EncheresController {


@GetMapping("/encheres")
    public String Home (Model model) {
return "index";

}


}
