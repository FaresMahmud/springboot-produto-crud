package com.example.crud.controller;

import com.example.crud.model.Produto;
import com.example.crud.repository.ProdutoRepository;
import com.example.crud.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/formulario")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvarProduto(Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produto/listar";
    }
    @GetMapping("/listar")
    public String listarProduto(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos",produtos);
    return  "lista";
    }
    @GetMapping("deletar/{id}")
    public String excluirProduto(@PathVariable Integer id) {
        produtoRepository.deleteById(id);
        return "redirect:/produto/listar";
    }
    @GetMapping("/editar/{id}")
    public String   editarProduto(@PathVariable Integer id, Model model) {
        Optional<Produto> produto = produtoRepository.findById(id);
        model.addAttribute("produto", produto);
        return "formulario";
    }
}
