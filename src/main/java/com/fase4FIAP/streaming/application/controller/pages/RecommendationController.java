package com.fase4FIAP.streaming.application.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recommendation")
public class RecommendationController {

    @GetMapping
    public String recommendation() {
        return "recommendation";
    }
}
