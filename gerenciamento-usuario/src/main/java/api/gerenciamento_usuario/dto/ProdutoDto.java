package api.gerenciamento_usuario.dto;

import api.gerenciamento_usuario.entity.ProdutoEntity;


public class ProdutoDto {

    private Long id;
    private String nome;
    private double preco;
    private Long categoriaId;

    public ProdutoDto() {
    }

    public ProdutoDto(ProdutoEntity produto) {
        this.setId(produto.getId());
        this.setNome(produto.getNome());
        this.setPreco(produto.getPreco());
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}