package br.com.treinamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/health")
@Schema(name = "Health Controller", description = "Testar status da aplicação - Online")
public class HealthController {

    @GetMapping
    public String health() {
        return "Server UP!";
    }
}