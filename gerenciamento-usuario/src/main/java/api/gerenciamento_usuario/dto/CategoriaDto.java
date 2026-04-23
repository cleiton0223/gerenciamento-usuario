package api.gerenciamento_usuario.dto;

import api.gerenciamento_usuario.entity.CategoriaEntity;

public class CategoriaDto {

    private Long id;
    private String nome;

    public CategoriaDto() {
    }
    public CategoriaDto(CategoriaEntity categoria) {
        this.setNome(categoria.getNome());
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}