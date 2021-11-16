package com.diogobis.atividade1.service;

import java.util.List;
import java.util.Optional;

import com.diogobis.atividade1.domain.Usuario;
import com.diogobis.atividade1.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllList(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findOne(Long id){
        log.debug("Request to get Usuario : {}", id);
        return usuarioRepository.findById(id);
    }

    public void delete(long id){
        log.debug("Request to delete Usuario : {}", id);
        usuarioRepository.deleteById(id);
    }

    public Usuario save(Usuario usuario){
        log.debug("Request to save Usuario : {}", usuario);
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }
}
