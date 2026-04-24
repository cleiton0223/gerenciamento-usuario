package api.gerenciamento_usuario.service;

import api.gerenciamento_usuario.dto.CategoriaDto;
import api.gerenciamento_usuario.entity.CategoriaEntity;
import api.gerenciamento_usuario.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoriaService {


    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public boolean cadastro(CategoriaDto categoriaDto) {

        for (CategoriaEntity categoria : categoriaRepository.findAll()) {
            if (categoria.getNome().equalsIgnoreCase(categoriaDto.getNome())) {
                throw new IllegalArgumentException("Categoria já cadastrada");
            }
        }

        CategoriaEntity novaCategoria = new CategoriaEntity(categoriaDto);
        categoriaRepository.save(novaCategoria);

        return true;
    }
    public List<CategoriaDto> listarCategoria() {

        List<CategoriaDto> categorias = new ArrayList<>();

        for (CategoriaEntity lista : categoriaRepository.findAll()) {
            CategoriaDto categoria = new CategoriaDto();

            categoria.setId(lista.getId());
            categoria.setNome(lista.getNome());


            categorias.add(categoria);
        }
        return categorias;
    }

}
