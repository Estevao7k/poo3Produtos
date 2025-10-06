import java.math.BigDecimal;
import java.time.LocalDate;

public class Produto {
    String Codigo;
    String Nome;
    String Descricao;
    LocalDate DataFabricacao;
    LocalDate DataValidade;
    BigDecimal PrecoCompra;
    BigDecimal PrecoVenda;
    int QuantidadeEstoque;
    Categoria Categoria;

    public Produto (String Codigo, String Nome, String Descricao, LocalDate DataFabricacao, LocalDate DataValidade, BigDecimal PrecoCompra, BigDecimal PrecoVenda, int QuantidadeEstoque, Categoria Categoria){
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
}
