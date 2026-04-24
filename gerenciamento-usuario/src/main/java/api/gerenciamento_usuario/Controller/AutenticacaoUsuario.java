package api.gerenciamento_usuario.Controller;

import api.gerenciamento_usuario.dto.Autenticacao;
import api.gerenciamento_usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class AutenticacaoUsuario {

    private final UsuarioService usuarioService;

    public AutenticacaoUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@RequestBody Autenticacao autenticacao) {

        if (autenticacao.getLogin() == null || autenticacao.getSenha() == null ||
                autenticacao.getLogin().isBlank() || autenticacao.getSenha().isBlank()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login e senha são obrigatórios");
        }

        boolean validar = usuarioService.autenticacao(autenticacao.getLogin(), autenticacao.getSenha());

        if (validar) {
            return ResponseEntity.status(HttpStatus.OK).body("Autenticação realizada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
        }
    }
}