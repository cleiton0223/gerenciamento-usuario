package api.gerenciamento_usuario.service;
import api.gerenciamento_usuario.dto.RespostaUsuarioDto;
import api.gerenciamento_usuario.dto.UsuarioDto;
import api.gerenciamento_usuario.entity.UsuarioEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private List<UsuarioEntity> arraylist = new ArrayList<>();

    public boolean cadastrar(UsuarioDto usuarioDto) {

        for (UsuarioEntity usuario : arraylist) {

            if (usuario.getCpf().equals(usuarioDto.getCpf())) {
                return false;
            }

            if (usuario.getLogin().equals(usuarioDto.getLogin())) {
                return false;
            }
        }

        UsuarioEntity novoUsuario = new UsuarioEntity();

        novoUsuario.setLogin(usuarioDto.getLogin());
        novoUsuario.setCpf(usuarioDto.getCpf());
        novoUsuario.setNome(usuarioDto.getNome());
        novoUsuario.setSenha(usuarioDto.getSenha());

        arraylist.add(novoUsuario);

        return true;
    }
    public List<RespostaUsuarioDto> listarUsuario() {

        List<RespostaUsuarioDto> colaboradores = new ArrayList<>();

        for (UsuarioEntity lista : arraylist) {

            RespostaUsuarioDto usuario = new RespostaUsuarioDto();
            usuario.setNome(lista.getNome());
            usuario.setCpf(lista.getCpf());
            usuario.setLogin(lista.getLogin());

            colaboradores.add(usuario);
        }

        return colaboradores;
    }

}
