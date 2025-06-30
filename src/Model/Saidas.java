/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Usuário
 */
public class Saidas {
    private int    Id;
    private int    idProduto;
    private String Produto;
    private String dataSaida;
    private int    Quantidade;
    private String Destinatário;

    public int getId(){
        return Id;
    }
    
    public void setId(int Id){
        this.Id = Id;
    }

    public int getIdProduto(){
        return idProduto;
    }

    public void setIdProduto(int idProduto){
        this.idProduto = idProduto;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String Produto) {
        this.Produto = Produto;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public String getDestinatário() {
        return Destinatário;
    }

    public void setDestinatário(String Destinatário) {
        this.Destinatário = Destinatário;
    }

}