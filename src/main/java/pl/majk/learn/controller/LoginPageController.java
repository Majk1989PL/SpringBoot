package pl.majk.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;

@Controller
public class LoginPageController {

    @GET
    @RequestMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }
}
