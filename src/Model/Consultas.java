/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Usuário
 */
public class Consultas {
    private int idProduto;
    private String nomeProduto;
    private int totalEntradas;
    private int totalSaidas;
    private int estoqueAtual;
    private String categoria;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int id) {
        this.idProduto = id;
    }

    public int getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(int totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public int getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(int totalSaidas) {
        this.totalSaidas = totalSaidas;

    }
    public int getEstoqueAtual(){
        return estoqueAtual;
    }
    public void setEstoqueAtual(int estoqueAtual){
        this.estoqueAtual = estoqueAtual;
    }
    public String getCategoria(){
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

}