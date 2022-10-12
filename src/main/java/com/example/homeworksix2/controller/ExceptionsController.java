package com.example.homeworksix2.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionsController implements ErrorController {
    private final static String PATH = "/error";
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        return "Sorry, something went wrong. Try again, be sure that all IDs are real objects IDs";
    }
}

