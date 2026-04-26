package com.example.crud.service;

import com.example.crud.model.Categoria;
import com.example.crud.model.Produto;
import com.example.crud.repository.CategoriaRepository;
import com.example.crud.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void salvar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new RuntimeException("O nome do produto e obrigatorio.");
        }

        if (produto.getValor() <= 0) {
            throw new RuntimeException("O valor deve ser maior que zero.");
        }

        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new RuntimeException("A categoria do produto e obrigatoria.");
        }

        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada."));

        produto.setCategoria(categoria);
        produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto nao encontrado."));
    }

    public void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }
}
