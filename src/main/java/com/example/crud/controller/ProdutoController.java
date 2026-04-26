package com.example.crud.controller;

import com.example.crud.model.Produto;
import com.example.crud.service.CategoriaService;
import com.example.crud.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/formulario")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvarProduto(Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/listar")
    public String listarProduto(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "lista";
    }

    @GetMapping("/deletar/{id}")
    public String excluirProduto(@PathVariable Integer id) {
        produtoService.excluir(id);
        return "redirect:/produto/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Integer id, Model model) {
        model.addAttribute("produto", produtoService.buscarPorId(id));
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formulario";
    }
}
