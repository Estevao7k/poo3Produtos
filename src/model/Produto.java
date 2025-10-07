package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Produto {
    private String Codigo;
    private String Nome;
    private String Descricao;
    private LocalDate DataFabricacao;
    private LocalDate DataValidade;
    private BigDecimal PrecoCompra;
    private BigDecimal PrecoVenda;
    private int QuantidadeEstoque;
    private Categoria Categoria;

    public Produto(String Codigo, String Nome, String Descricao, LocalDate DataFabricacao,
                   LocalDate DataValidade, BigDecimal PrecoCompra, BigDecimal PrecoVenda,
                   int QuantidadeEstoque, Categoria Categoria) {
        this.Codigo = Codigo;
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.DataFabricacao = DataFabricacao;
        this.DataValidade = DataValidade;
        this.PrecoCompra = PrecoCompra;
        this.PrecoVenda = PrecoVenda;
        this.QuantidadeEstoque = QuantidadeEstoque;
        this.Categoria = Categoria;
    }

    public String getCodigo() { return this.Codigo; }
    public String getNome() { return this.Nome; }
    public String getDescricao() { return this.Descricao; }
    public LocalDate getDataFabricacao() { return this.DataFabricacao; }
    public LocalDate getDataValidade() { return this.DataValidade; }
    public BigDecimal getPrecoCompra() { return this.PrecoCompra; }
    public BigDecimal getPrecoVenda() { return this.PrecoVenda; }
    public int getQuantidadeEstoque() { return this.QuantidadeEstoque; }
    public Categoria getCategoria() { return this.Categoria; }

    public String CsvString() {
        return String.join(";",
                this.Codigo,
                this.Nome,
                this.Descricao,
                this.DataFabricacao.toString(),
                this.DataValidade.toString(),
                this.PrecoCompra.toString(),
                this.PrecoVenda.toString(),
                Integer.toString(this.QuantidadeEstoque),
                Integer.toString(this.Categoria.getId()),
                this.Categoria.getNomeProduto(),
                this.Categoria.getDescricao(),
                this.Categoria.getSetor()
        );
    }
}