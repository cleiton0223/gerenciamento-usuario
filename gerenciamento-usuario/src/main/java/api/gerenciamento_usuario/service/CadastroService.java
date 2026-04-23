package api.gerenciamento_usuario.service;
import api.gerenciamento_usuario.dto.CategoriaDto;
import api.gerenciamento_usuario.dto.ProdutoDto;
import api.gerenciamento_usuario.entity.CategoriaEntity;
import api.gerenciamento_usuario.entity.ProdutoEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroService {


    private List<CategoriaEntity> categorias = new ArrayList<>();
    private List<ProdutoEntity> produtos = new ArrayList<>();


    public void cadastrarCategoria(CategoriaDto categoriaDto) {

        CategoriaEntity novaCategoria = new CategoriaEntity();

        novaCategoria.setId(categoriaDto.getId());
        novaCategoria.setNome(categoriaDto.getNome());

        categorias.add(novaCategoria);
    }
    public List<CategoriaDto> listarCategoria() {

        List<CategoriaDto> listaCategoriasDto = new ArrayList<>();

        for (CategoriaEntity entidade : categorias) {
            CategoriaDto dto = new CategoriaDto();

            dto.setId(entidade.getId());
            dto.setNome(entidade.getNome());

            listaCategoriasDto.add(dto);
        }

        return listaCategoriasDto;
    }
    public boolean cadastrarProduto(ProdutoDto produtoDto) {

        for (CategoriaEntity categoria : categorias) {

            if (categoria.getId().equals(produtoDto.getCategoriaId())) {

                ProdutoEntity novoProduto = new ProdutoEntity();

                novoProduto.setId(produtoDto.getId());
                novoProduto.setNome(produtoDto.getNome());
                novoProduto.setPreco(produtoDto.getPreco());
                novoProduto.setCategoria(categoria);

                produtos.add(novoProduto);
                return true;
            }
        }
        return false;
    }

}