package api.gerenciamento_usuario.Controller;

import api.gerenciamento_usuario.dto.ProdutoDto;
import api.gerenciamento_usuario.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class  ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ProdutoDto dto) {

        boolean retorno = produtoService.cadastrarProduto(dto);

        if (retorno) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDto>> listagem() {
        return ResponseEntity.ok(produtoService.listarProduto());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoDto>> listagemCategoria(@PathVariable("id") Long id) {
        return ResponseEntity.ok(produtoService.listarCategoriaProduto(id));
    }
    @PutMapping("atualizarr")
    public ResponseEntity<String> atualizar(@RequestBody ProdutoDto dto) {

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }

        boolean retorno = produtoService.atualizarprodutos(dto);

        if (retorno) {
            return ResponseEntity.ok("Produto atualizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }
}
