package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 100)
    private String nome;
    private String descricao;
    private int valor;
    private int quantidade;
    private String imagem;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produto(Categoria categoria, String imagem, int quantidade, int valor, String descricao, String nome, int id) {
        this.categoria = categoria;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.id = id;
    }

    public Produto(String nome, String descricao, int valor, int quantidade, String imagem, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto(int id, String nome, String descricao, int valor, int quantidade, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.imagem = imagem;
    }

    public Produto(String nome, String descricao, int valor, int quantidade, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.imagem = imagem;
    }

    public Produto() {
    }
}

