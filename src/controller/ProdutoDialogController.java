package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Categoria;
import model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoDialogController {

    @FXML private TextField CampoCodigo;
    @FXML private TextField CampoNome;
    @FXML private TextField CampoDescricao;
    @FXML private DatePicker CampoDataFabricacao;
    @FXML private DatePicker CampoDataValidade;
    @FXML private TextField CampoPrecoCompra;
    @FXML private TextField CampoPrecoVenda;
    @FXML private TextField CampoEstoque;

    private Stage dialogStage;
    private Produto produto;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        if (produto != null) {
            CampoCodigo.setText(produto.getCodigo());
            CampoNome.setText(produto.getNome());
            CampoDescricao.setText(produto.getDescricao());
            CampoDataFabricacao.setValue(produto.getDataFabricacao());
            CampoDataValidade.setValue(produto.getDataValidade());
            CampoPrecoCompra.setText(produto.getPrecoCompra().toString());
            CampoPrecoVenda.setText(produto.getPrecoVenda().toString());
            CampoEstoque.setText(Integer.toString(produto.getQuantidadeEstoque()));
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public Produto getProduto() {
        return produto;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String codigo = CampoCodigo.getText();
            String nome = CampoNome.getText();
            String descricao = CampoDescricao.getText();
            LocalDate dataFabricacao = CampoDataFabricacao.getValue();
            LocalDate dataValidade = CampoDataValidade.getValue();
            BigDecimal precoCompra = new BigDecimal(CampoPrecoCompra.getText());
            BigDecimal precoVenda = new BigDecimal(CampoPrecoVenda.getText());
            int quantidadeEstoque = Integer.parseInt(CampoEstoque.getText());

            Categoria categoriaPadrao = new Categoria(1, "Geral", "model.Categoria Padrão", "Setor Geral");

            this.produto = new Produto(codigo, nome, descricao, dataFabricacao, dataValidade,
                    precoCompra, precoVenda, quantidadeEstoque, categoriaPadrao);

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (CampoCodigo.getText() == null || CampoCodigo.getText().isEmpty()) errorMessage += "Código inválido!\n";
        if (CampoNome.getText() == null || CampoNome.getText().isEmpty()) errorMessage += "Nome inválido!\n";
        if (CampoPrecoCompra.getText() == null || !CampoPrecoCompra.getText().matches("\\d+(\\.\\d+)?")) errorMessage += "Preço de compra inválido!\n";
        if (CampoPrecoVenda.getText() == null || !CampoPrecoVenda.getText().matches("\\d+(\\.\\d+)?")) errorMessage += "Preço de venda inválido!\n";
        if (CampoEstoque.getText() == null || !CampoEstoque.getText().matches("\\d+")) errorMessage += "Estoque inválido!\n";

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor, corrija os campos inválidos.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}