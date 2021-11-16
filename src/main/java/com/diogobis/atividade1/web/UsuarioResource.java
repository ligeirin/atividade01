package com.diogobis.atividade1.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import com.diogobis.atividade1.domain.Usuario;
import com.diogobis.atividade1.service.UsuarioService;

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
@RequestMapping("/usuarios")
public class UsuarioResource {
    private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

    private final UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    // @GetMapping(path="/")
    // public String helloApp(){
    //     List<Usuario> list = usuarioService.findAllList();
    //     String usuarioList = "<table>";
    //     for (int i = 0; i < list.size(); i++){
    //         usuarioList += "<tr>";
    //         usuarioList += "<td>"+i+"</td>";
    //         usuarioList += "<td>"+list.get(i).getNome()+"</td>";
    //         usuarioList += "</tr>";
    //     }
    //     usuarioList += "</table>";
    //     return usuarioList;
    // }

    @GetMapping(path="/criar/{name}")
    public String helloApp(@PathVariable String name){
        Usuario u = new Usuario();
        u.setNome(name);
        usuarioService.save(u);
        return "Criado<br>Nome: "+name;
    }

    /*
    {@code GET /usuarios/:id} : get the "id" Usuario.

    @param id o id do usuário que será buscado.
    @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o usuário, ou com status {@code 404 (Not Found)}.
    */
    @GetMapping(path="/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
        log.debug("REST request to get Usuario : {}",id);
        Optional<Usuario> usuario = usuarioService.findOne(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok().body(usuario.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> lista = usuarioService.findAllList();
        if (lista.size() > 0){
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /*
    {@code PUT /usuarios} : Updates an existing Usuario.

    @param usuario the user that is to be updated.
    @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the update Usuario in the body,
    or with status {@code 400 (Bad Request)} if usuario is not valid,
    or with status {@code 500 (Internal Server Errror)} if usuario can't be updated.

    @throws URISyntaxException if the Location URI syntax is incorrect
    */
    @PutMapping("/")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) throws URISyntaxException {
        log.debug("REST request to update Usuario : {}",usuario);
        if(usuario.getId() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid Usuario id null");
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.ok().body(result);
    }


    /*
    {@code POST /} : create a new Usuario.

    @param usuario the pessoa to create.
    @return the {@link ResponseEntity} with status {@code 200 (OK)} eand with body the new Usuario, or with status {@code 404 (Not Found)}.
    @throws URISyntaxException if the Location URI syntax is incorrect
    */
    @PostMapping("/")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) throws URISyntaxException{
        log.debug("REST request to save Usuario : {}",usuario);
        if (usuario.getId() != null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Um novo usuário não pode ter um ID");
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.created(new URI("/api/usuarios/"+ result.getId())).body(result);
    }
    

    /*
    {@code DELETE /:id} delete Usuario with "id".

    @param id the id of the Usuario that is going to be deleted
    @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        log.debug("REST request to delete Usuario : {}", id);

        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
