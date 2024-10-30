package com.demo.service;

import com.demo.model.Sentimento;
import com.demo.repository.SentimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SentimentoService {

    @Autowired
    private SentimentoRepository sentimentoRepository;

    public List<Sentimento> listarSentimentos() {
        return sentimentoRepository.findAll();
    }

    public Optional<Sentimento> obterSentimentoPorId(Long id) {
        return sentimentoRepository.findById(id);
    }

    public Sentimento criarSentimento(Sentimento sentimento) {
        return sentimentoRepository.save(sentimento);
    }

    public Sentimento atualizarSentimento(Long id, Sentimento sentimentoDetails) {
        Sentimento sentimento = sentimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Sentimento não encontrado"));
        sentimento.setNome(sentimentoDetails.getNome());
        return sentimentoRepository.save(sentimento);
    }

    public void deletarSentimento(Long id) {
        if (!sentimentoRepository.existsById(id)) {
            throw new RuntimeException("Sentimento não encontrado");
        }
        sentimentoRepository.deleteById(id);
    }
}
