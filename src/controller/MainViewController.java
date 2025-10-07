package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GerenciadorProdutos;
import model.Produto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class MainViewController {

    @FXML private TableView<Produto> TabelaProdutos;
    @FXML private TableColumn<Produto, String> ColunaCodigo;
    @FXML private TableColumn<Produto, String> ColunaNome;
    @FXML private TableColumn<Produto, BigDecimal> ColunaPrecoVenda;
    @FXML private TableColumn<Produto, Integer> ColunaEstoque;
    @FXML private TableColumn<Produto, String> ColunaCategoria;

    private GerenciadorProdutos gerenciador = new GerenciadorProdutos();
    private final String NOME_ARQUIVO_CSV = "produtos.csv";

    @FXML
    public void initialize() {
        gerenciador.carregarDeCsv(NOME_ARQUIVO_CSV);
        configurarTabela();
        atualizarTabela();
    }

    private void configurarTabela() {
        ColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        ColunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        ColunaPrecoVenda.setCellValueFactory(new PropertyValueFactory<>("PrecoVenda"));
        ColunaEstoque.setCellValueFactory(new PropertyValueFactory<>("QuantidadeEstoque"));
        ColunaCategoria.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategoria().getNomeProduto())
        );
    }

    private void atualizarTabela() {
        TabelaProdutos.setItems(FXCollections.observableArrayList(gerenciador.ListarTodosProdutos()));
    }

    @FXML
    private void onAdicionarClick() {
        mostrarDialogoProduto(null);
    }

    @FXML
    private void onEditarClick() {
        Produto produtoSelecionado = TabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            mostrarDialogoProduto(produtoSelecionado);
        } else {
            mostrarAlerta("Nenhum produto selecionado", "Por favor, selecione um produto para editar.");
        }
    }

    @FXML
    private void onExcluirClick() {
        Produto produtoSelecionado = TabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            Optional<ButtonType> resultado = mostrarConfirmacao("Excluir model.Produto", "Tem certeza que deseja excluir o produto: " + produtoSelecionado.getNome() + "?");
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                gerenciador.excluirProduto(produtoSelecionado.getCodigo());
                salvarEAtualizar();
            }
        } else {
            mostrarAlerta("Nenhum produto selecionado", "Por favor, selecione um produto para excluir.");
        }
    }

    private void mostrarDialogoProduto(Produto produto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProdutoDialog.fxml"));
            BorderPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(produto == null ? "Adicionar model.Produto" : "Editar model.Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(TabelaProdutos.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ProdutoDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduto(produto);

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                if (produto == null) { // Adicionar novo
                    gerenciador.AdicionarProduto(controller.getProduto());
                } else { // Editar existente
                    // A exclusão e adição simula uma atualização
                    gerenciador.excluirProduto(produto.getCodigo());
                    gerenciador.AdicionarProduto(controller.getProduto());
                }
                salvarEAtualizar();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarEAtualizar() {
        gerenciador.salvarParaCsv(NOME_ARQUIVO_CSV);
        atualizarTabela();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private Optional<ButtonType> mostrarConfirmacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }
}