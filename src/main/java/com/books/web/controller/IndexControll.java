package com.books.web.controller;


import com.books.dao.AuthorD;
import com.books.dao.GenreD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class IndexControll {


    @Autowired
    GenreD genreD;

    @Autowired
    AuthorD authorD;

    private static final Logger LOGGER =
            LogManager.getLogger(IndexControll.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        LOGGER.debug("return index page");

        model.addAttribute("genreList", genreD.findAll());
        model.addAttribute("authorList", authorD.findAll());

        return "index/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model ) {

            LOGGER.debug("return login page");
            return "index/loginPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {
        LOGGER.debug("403 page");
            LOGGER.error("not enough priveleges");
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        return "index/403";
    }

}
