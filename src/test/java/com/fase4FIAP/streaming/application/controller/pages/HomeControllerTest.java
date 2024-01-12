package com.fase4FIAP.streaming.application.controller.pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HomeControllerTest {
    final String baseUrl = "/";

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        HomeController homeController = new HomeController();
        mockMvc = MockMvcBuilders.standaloneSetup(homeController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void allowFavoriteVideo() throws Exception {
        String message = "index";
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(message));
        ;
    }
}
