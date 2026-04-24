package api.gerenciamento_usuario.service;

import api.gerenciamento_usuario.dto.ProdutoDto;
import api.gerenciamento_usuario.entity.CategoriaEntity;
import api.gerenciamento_usuario.entity.ProdutoEntity;
import api.gerenciamento_usuario.repository.CategoriaRepository;
import api.gerenciamento_usuario.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
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
}