package pl.majk.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.GET;


@Controller
public class MainPageController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);

    @GET
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        LOG.info(" showMainPage()");
        return "index";
    }
}
