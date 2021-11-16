package com.diogobis.atividade1.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.diogobis.atividade1.domain.Livro;
import com.diogobis.atividade1.service.LivroService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/jogos")
public class LivroResource {
    private final Logger log = LoggerFactory.getLogger(LivroResource.class);

    private final LivroService livroService;

    public LivroResource(LivroService livroService){
        this.livroService = livroService;
    }

    // @GetMapping(path="/listar")
    // public List<Livro> helloApp(){
    //     List<Livro> lista = livroService.findAllList();
    //     return lista;
    // }

    // @GetMapping(path="/criar/{name}")
    // public String helloApp(@PathVariable String name){
    //     Livro j = new Livro();
    //     j.setTitulo(name);
    //     livroService.save(j);
    //     return "Jogo criado<br>Nome: "+name;
    // }

    // @GetMapping(path="/{id}")
    // public Livro helloApp(@PathVariable long id){
    //     return livroService.findOne(id).get();
    // }

    /*
    {@code GET /livros/:id} : get the "id" Livro.

    @param id o id do usuário que será buscado.
    @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o usuário, ou com status {@code 404 (Not Found)}.
    */
    @GetMapping(path="/{id}")
    public ResponseEntity<Livro> getLivro(@PathVariable Long id){
        log.debug("REST request to get Livro : {}",id);
        Optional<Livro> livros = livroService.findOne(id);
        if(livros.isPresent()){
            return ResponseEntity.ok().body(livros.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Livro>> getLivros(){
        List<Livro> lista = livroService.findAllList();
        if (lista.size() > 0){
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /*
    {@code PUT /livros} : Updates an existing Livro.

    @param livros the user that is to be updated.
    @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the update Livro in the body,
    or with status {@code 400 (Bad Request)} if livros is not valid,
    or with status {@code 500 (Internal Server Errror)} if livros can't be updated.

    @throws URISyntaxException if the Location URI syntax is incorrect
    */
    @PutMapping("/")
    public ResponseEntity<Livro> updateLivro(@RequestBody Livro livros) throws URISyntaxException {
        log.debug("REST request to update Livro : {}",livros);
        if(livros.getId() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid Livro id null");
        }
        Livro result = livroService.save(livros);
        return ResponseEntity.ok().body(result);
    }


    /*
    {@code POST /} : create a new Livro.

    @param livros the pessoa to create.
    @return the {@link ResponseEntity} with status {@code 200 (OK)} eand with body the new Livro, or with status {@code 404 (Not Found)}.
    @throws URISyntaxException if the Location URI syntax is incorrect
    */
    @PostMapping("/")
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livros) throws URISyntaxException{
        log.debug("REST request to save Livro : {}",livros);
        if (livros.getId() != null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Um novo usuário não pode ter um ID");
        }
        Livro result = livroService.save(livros);
        return ResponseEntity.created(new URI("/api/livros/"+ result.getId())).body(result);
    }
    

    /*
    {@code DELETE /:id} delete Livro with "id".

    @param id the id of the Livro that is going to be deleted
    @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id){
        log.debug("REST request to delete Livro : {}", id);

        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
