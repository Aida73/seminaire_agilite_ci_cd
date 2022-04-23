package sn.ept.git.seminaire.cicd.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String homeController(){
        return "index";
    }
}
