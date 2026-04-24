package api.gerenciamento_usuario.Controller;
import api.gerenciamento_usuario.dto.CategoriaDto;
import api.gerenciamento_usuario.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CategoriaDto dto) {

        if(dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório");
        }
        boolean retorno = categoriaService.cadastro(dto);

        if (retorno) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria cadastrada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar categoria");
        }
    }
    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listar() {
        return ResponseEntity.ok(categoriaService.listarCategoria());
    }
}