package com.diogobis.atividade1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_livro")
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", length = 64)
    private String titulo;

    private String descricao;
    private float price;
    private String imagemUrl;
    private String trailerUrl;
    private String categoria; //jogo, software, ferramenta, etc.
    private String genero; //ação, aventura, plataforma, etc.
    private String tipoProjeto; //para download, por navegador, etc.

    //relação entre jogos e usuários
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario; //segundo o site aqui é tipo Usuario, mas não seria long
}
