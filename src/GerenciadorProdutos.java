import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private List<Produto> ListaProdutos;

    public GerenciadorProdutos(){
        this.ListaProdutos = new ArrayList<>();
    }

    public void AdicionarProduto(Produto ProdutoParaAdicionar){
        this.ListaProdutos.add(ProdutoParaAdicionar);

    }

    public List<Produto> ListarTodosProdutos(){
        return this.ListaProdutos;
    }

    public Produto buscarProdutoPorCodigo(String CodigoParaBuscar){
        for (Produto produto : this.ListaProdutos){
            if (produto.getCodigo().equals(CodigoParaBuscar)){
                return  produto;
            }
        }
        return null;
    }


}
