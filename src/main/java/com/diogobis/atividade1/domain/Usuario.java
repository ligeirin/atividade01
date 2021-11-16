package com.diogobis.atividade1.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 64)
    private String nome;

    @Column(name = "senha", length = 24)
    private String senha;

    private String email;
    private String bio;
    // private String socialMedia;
    private String uf;
    private String pais;

    private boolean isActive;

    //relação entre jogos e usuários
    @OneToMany(mappedBy = "usuario")
    private List<Livro> livros;
}
