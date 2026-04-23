package api.gerenciamento_usuario.entity;
import api.gerenciamento_usuario.dto.ProdutoDto;
import jakarta.persistence.*;

@Entity
@Table(name = "PRODUTO")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @ManyToOne
    private CategoriaEntity categoria;

    public ProdutoEntity(Long id, String nome, Double preco, CategoriaEntity categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public ProdutoEntity() {
    }

    public ProdutoEntity(ProdutoDto produtoDto) {
        this.setNome(produtoDto.getNome());
        this.setPreco(produtoDto.getPreco());
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
}