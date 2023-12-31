package com.fase4FIAP.streaming.aplicacao.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/favorite")
public class FavoriteVideoController {

    @GetMapping
    public String favoriteVideo() {
        return "favorite";
    }
}
