package com.demo.controller;

import com.demo.model.Sentimento;
import com.demo.service.SentimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sentimentos")
public class SentimentoController {

    @Autowired
    private SentimentoService sentimentoService;

    @GetMapping
    public List<Sentimento> listarSentimentos() {
        return sentimentoService.listarSentimentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sentimento> obterSentimentoPorId(@PathVariable Long id) {
        return sentimentoService.obterSentimentoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sentimento criarSentimento(@RequestBody Sentimento sentimento) {
        return sentimentoService.criarSentimento(sentimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sentimento> atualizarSentimento(@PathVariable Long id, @RequestBody Sentimento sentimentoDetails) {
        return ResponseEntity.ok(sentimentoService.atualizarSentimento(id, sentimentoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSentimento(@PathVariable Long id) {
        sentimentoService.deletarSentimento(id);
        return ResponseEntity.noContent().build();
    }
}
