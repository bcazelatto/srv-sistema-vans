package br.com.treinamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/health")
@Schema(name = "Hello Controller", description = "Testar se a aplicação esta online")
public class HelloController {

    @GetMapping
    public String health() {
        return "Server UP!";
    }
}