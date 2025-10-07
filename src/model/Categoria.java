package model;

public class Categoria {
        private int Id;
        private String NomeProduto;
        private String Descricao;
        private String Setor;

        public Categoria (int Id, String NomeProduto, String Descricao, String Setor) {
            this.Id = Id;
            this.NomeProduto = NomeProduto;
            this.Descricao = Descricao;
            this.Setor = Setor;

        }

    public int getId() {
        return this.Id;
    }

    public String getNomeProduto(){
        return this.NomeProduto;
    }

    public String getDescricao(){
        return this.Descricao;
    }

    public String getSetor(){
        return this.Setor;

    }
}
