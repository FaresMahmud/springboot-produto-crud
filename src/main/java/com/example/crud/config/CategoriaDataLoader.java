package com.example.crud.config;

import com.example.crud.model.Categoria;
import com.example.crud.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaDataLoader {

    @Bean
    CommandLineRunner carregarCategoriasPadrao(CategoriaRepository categoriaRepository) {
        return args -> {
            criarSeNaoExistir(categoriaRepository, "Action Figure");
            criarSeNaoExistir(categoriaRepository, "Outros");
        };
    }

    private void criarSeNaoExistir(CategoriaRepository categoriaRepository, String nome) {
        if (!categoriaRepository.existsByNome(nome)) {
            categoriaRepository.save(new Categoria(nome));
        }
    }
}
