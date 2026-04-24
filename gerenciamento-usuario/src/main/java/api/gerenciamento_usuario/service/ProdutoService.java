package api.gerenciamento_usuario.service;

import api.gerenciamento_usuario.dto.ProdutoDto;
import api.gerenciamento_usuario.entity.CategoriaEntity;
import api.gerenciamento_usuario.entity.ProdutoEntity;
import api.gerenciamento_usuario.repository.CategoriaRepository;
import api.gerenciamento_usuario.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public boolean cadastrarProduto(ProdutoDto produto) {

        for (ProdutoEntity existente : produtoRepository.findAll()) {
            if (existente.getNome().equalsIgnoreCase(produto.getNome())) {
                throw new IllegalArgumentException("Produto já cadastrado");
            }
        }

        Optional<CategoriaEntity> categoria = categoriaRepository.findById(produto.getCategoriaId());

        if (categoria.isEmpty()) {
            return false;
        }

        ProdutoEntity novoProduto = new ProdutoEntity(produto);
        novoProduto.setCategoria(categoria.get());

        produtoRepository.save(novoProduto);

        return true;
    }
    public List<ProdutoDto> listarProduto() {
        List<ProdutoDto> produtos = new ArrayList<>();

        for (ProdutoEntity lista : produtoRepository.findAll()) {
            ProdutoDto dto = new ProdutoDto(lista);

            if (lista.getCategoria() != null) {
                dto.setCategoriaId(lista.getCategoria().getId());
            }

            produtos.add(dto);
        }
        return produtos;
    }
    public List<ProdutoDto> listarCategoriaProduto(Long categoriaId) {
        List<ProdutoDto> produtos = new ArrayList<>();

        for (ProdutoEntity lista : produtoRepository.findAll()) {
            if (lista.getCategoria() != null && lista.getCategoria().getId().equals(categoriaId)) {

                ProdutoDto produto = new ProdutoDto(lista);

                produtos.add(produto);
            }
        }
        return produtos;
    }
    public boolean atualizarprodutos(ProdutoDto produto) {

        for (ProdutoEntity atualizar : produtoRepository.findAll()) {
            if (atualizar.getId().equals(produto.getId())) {

                atualizar.setNome(produto.getNome());
                atualizar.setPreco(produto.getPreco());

                Optional<CategoriaEntity> categoria = categoriaRepository.findById(produto.getCategoriaId());

                if (categoria.isEmpty()) {
                    return false;
                }

                atualizar.setCategoria(categoria.get());
                produtoRepository.save(atualizar);
                return true;
            }
        }
        return false;
    }
}