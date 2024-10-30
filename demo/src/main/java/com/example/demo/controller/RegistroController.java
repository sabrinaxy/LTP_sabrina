package com.demo.controller;

import com.demo.model.Registro;
import com.demo.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping
    public List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registro> obterRegistroPorId(@PathVariable Long id) {
        Optional<Registro> registro = registroRepository.findById(id);
        return registro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Registro criarRegistro(@RequestBody Registro registro) {
        return registroRepository.save(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registro> atualizarRegistro(@PathVariable Long id, @RequestBody Registro registroDetails) {
        Optional<Registro> registroOpt = registroRepository.findById(id);
        
        if (!registroOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Registro registro = registroOpt.get();
        registro.setDescricao(registroDetails.getDescricao());
        registro.setSentimento(registroDetails.getSentimento());

        Registro atualizado = registroRepository.save(registro);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable Long id) {
        if (!registroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        registroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
