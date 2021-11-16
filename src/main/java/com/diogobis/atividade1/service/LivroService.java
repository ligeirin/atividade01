package com.diogobis.atividade1.service;

import java.util.List;
import java.util.Optional;

import com.diogobis.atividade1.domain.Livro;
import com.diogobis.atividade1.repository.LivroRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    private final Logger log = LoggerFactory.getLogger(LivroService.class);

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<Livro> findAllList(){
        return livroRepository.findAll();
    }

    public Optional<Livro> findOne(Long id){
        log.debug("Request to get Livro : {}", id);
        return livroRepository.findById(id);
    }

    public void delete(long id){
        log.debug("Request to delete Livro : {}", id);
        livroRepository.deleteById(id);
    }

    public Livro save(Livro livro){
        log.debug("Request to save Livro : {}");
        livro = livroRepository.save(livro);
        return livro;
    }
}
