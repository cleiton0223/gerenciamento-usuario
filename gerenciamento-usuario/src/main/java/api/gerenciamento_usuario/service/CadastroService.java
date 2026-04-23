package api.gerenciamento_usuario.service;
import api.gerenciamento_usuario.dto.CategoriaDto;
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

}