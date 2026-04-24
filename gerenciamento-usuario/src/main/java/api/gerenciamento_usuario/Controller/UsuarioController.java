package api.gerenciamento_usuario.Controller;
import api.gerenciamento_usuario.dto.RespostaUsuario;
import api.gerenciamento_usuario.dto.RespostaUsuarioDto;
import api.gerenciamento_usuario.dto.UsuarioDto;
import api.gerenciamento_usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping("/usuario")
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioDto dto) {

        boolean cadastrado = usuarioService.cadastrar(dto);

        if (cadastrado) {
            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("login já cadastrado");
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<RespostaUsuarioDto>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuario());
    }

    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<RespostaUsuarioDto> buscarUsuarioPorCpf(@PathVariable("cpf") String cpf) {
        try {
            RespostaUsuarioDto resposta = usuarioService.buscarUsuarioPorCpf(cpf);
            return ResponseEntity.ok(resposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/usuario/senha/{login}")
    public ResponseEntity<String> alterarSenha(@PathVariable String login, @RequestBody RespostaUsuario dto) {

        if (dto.getSenhaNova().length() < 8) {
            return ResponseEntity.badRequest().body("A senha deve ter pelo menos 8 caracteres.");
        }

        if (!dto.getSenhaNova().equals(dto.getSenhaNovaConfirmacao())) {
            return ResponseEntity.badRequest().body("A confirmação da nova senha está incorreta.");
        }

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setLogin(login);

        boolean senhaAlterada = usuarioService.atualizarSenha(dto.getSenhaAtual(), dto.getSenhaNova(), usuarioDto);

        if (!senhaAlterada) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha atual incorreta.");
        }

        return ResponseEntity.ok("Senha alterada com sucesso.");
    }

    @DeleteMapping("/usuario/{cpf}")
    public ResponseEntity<Void> remover(@PathVariable("cpf") String cpf) {
        try {
            usuarioService.removeUsuario(cpf);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}