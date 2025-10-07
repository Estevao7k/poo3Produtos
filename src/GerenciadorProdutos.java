import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.Map;

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

    public boolean excluirProduto(String codigoParaExcluir) {
        Produto produtoParaRemover = buscarProdutoPorCodigo(codigoParaExcluir);

        if (produtoParaRemover != null) {
            this.ListaProdutos.remove(produtoParaRemover);
            return true;
        }

        return false;
    }

    public void salvarParaCsv(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Produto produto : this.ListaProdutos) {
                String linhaCsv = produto.CsvString();

                writer.write(linhaCsv);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo CSV: " + e.getMessage());
        }
    }

    public void carregarDeCsv(String nomeArquivo) {
        this.ListaProdutos.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");

                if (campos.length == 12) {
                    try {
                        int categoriaId = Integer.parseInt(campos[8]);
                        String categoriaNome = campos[9];
                        String categoriaDescricao = campos[10];
                        String categoriaSetor = campos[11];
                        Categoria categoria = new Categoria(categoriaId, categoriaNome, categoriaDescricao, categoriaSetor);

                        String produtoCodigo = campos[0];
                        String produtoNome = campos[1];
                        String produtoDescricao = campos[2];
                        LocalDate dataFabricacao = LocalDate.parse(campos[3]);
                        LocalDate dataValidade = LocalDate.parse(campos[4]);
                        BigDecimal precoCompra = new BigDecimal(campos[5]);
                        BigDecimal precoVenda = new BigDecimal(campos[6]);
                        int quantidadeEstoque = Integer.parseInt(campos[7]);

                        Produto produto = new Produto(produtoCodigo, produtoNome, produtoDescricao, dataFabricacao,
                                dataValidade, precoCompra, precoVenda, quantidadeEstoque, categoria);

                        this.ListaProdutos.add(produto);

                    } catch (Exception e) {
                        System.out.println("Erro ao processar linha do CSV: " + linha + " | Erro: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo CSV: " + e.getMessage());
        }
    }

    public List<Produto> relatorioEstoqueBaixo() {
        return this.ListaProdutos.stream()
                .filter(produto -> produto.getQuantidadeEstoque() < 10)
                .collect(Collectors.toList());
    }

    public List<Produto> relatorioProdutosProximosVencimento() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(60);

        return this.ListaProdutos.stream()
                .filter(produto ->
                        produto.getDataValidade().isAfter(hoje) &&
                                produto.getDataValidade().isBefore(dataLimite)
                )
                .collect(Collectors.toList());
    }

    public Map<String, List<Produto>> relatorioProdutosAgrupadosPorSetor() {
        return this.ListaProdutos.stream()
                .collect(Collectors.groupingBy(produto -> produto.getCategoria().getSetor()));
    }

    public Map<String, Double> relatorioMargemLucroMediaPorCategoria() {
        return this.ListaProdutos.stream()
                .collect(Collectors.groupingBy(
                        produto -> produto.getCategoria().getNomeProduto(),
                        Collectors.averagingDouble(produto -> {
                            double precoCompra = produto.getPrecoCompra().doubleValue();
                            double precoVenda = produto.getPrecoVenda().doubleValue();
                            if (precoCompra > 0) {
                                return (precoVenda - precoCompra) / precoCompra * 100;
                            }
                            return 0.0;
                        })
                ));
    }
}