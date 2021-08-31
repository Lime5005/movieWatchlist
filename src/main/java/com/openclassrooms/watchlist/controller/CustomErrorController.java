package com.openclassrooms.watchlist.controller;

import com.openclassrooms.watchlist.service.MovieRatingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        int status = response.getStatus();
        if ( status == HttpStatus.NOT_FOUND.value()) {
            logger.error("Error with status not found code {} Happened!", status);
            return new ModelAndView("error");
        }
        logger.error("Some other error happened: {}", status);
        return new ModelAndView("error");
    }
}
