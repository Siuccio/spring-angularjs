package it.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hello {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserLogged() {

        return "index";
    }

}
