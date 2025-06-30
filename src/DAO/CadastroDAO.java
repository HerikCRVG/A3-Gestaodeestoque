package DAO;

import java.sql.*;
import java.util.*;


import javax.swing.JOptionPane;

import Datab.Datab;
import Model.CadastroProdutos;

public class CadastroDAO {

    private Connection conecta;

    public void cadastrarProdutos(CadastroProdutos obj){

        try {
            Connection conn = Datab.getConnection();

            String sql = "insert into produto (nomeProdutos, categoria, preco, fornecedor) values(?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getProduto());
            stmt.setString(2,obj.getCategoria());
            stmt.setDouble(3,obj.getValor());
            stmt.setString(4,obj.getFornecedor());

            stmt.execute();

            stmt.close();
        } catch (SQLException erro) {

            throw new RuntimeException(erro);
        }
    }

    public List<CadastroProdutos> listarProdutos() {
        List<CadastroProdutos> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto;";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CadastroProdutos p = new CadastroProdutos();
                p.setId(rs.getInt("idProduto"));
                p.setProduto(rs.getString("nomeProdutos"));
                p.setValor(rs.getDouble("preco"));
                p.setFornecedor(rs.getString("fornecedor"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void excluirProduto(int id){

        try {
            Connection conn = Datab.getConnection();
            String sql = "DELETE FROM produto WHERE idProduto = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public List<CadastroProdutos> pesquisarProduto(int id) {
        List<CadastroProdutos> lista = new ArrayList<>();
        try {
            Connection conn = Datab.getConnection();
            String sql = "SELECT * FROM produto WHERE idProduto = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CadastroProdutos p = new CadastroProdutos();
                p.setId(rs.getInt("idProduto"));
                p.setProduto(rs.getString("nomeProdutos"));
                p.setValor(rs.getDouble("preco"));
                p.setFornecedor(rs.getString("fornecedor"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    public void salvarEdicao(int id, CadastroProdutos obj){
        try {
            Connection conn = Datab.getConnection();

            String sql = "UPDATE produto SET nomeProdutos = ?, categoria = ?, preco = ?, fornecedor = ? WHERE idProduto = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getProduto());
            stmt.setString(2,obj.getCategoria());
            stmt.setDouble(3,obj.getValor());
            stmt.setString(4,obj.getFornecedor());
            stmt.setInt   (5, id);

            stmt.execute();

            stmt.close();  
        } catch (Exception erro) {
                throw new RuntimeException(erro);
        }
    }

    public List<CadastroProdutos> pesquisarNomeProdutos(String termo){
        List<CadastroProdutos> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE upper(nomeProdutos) like upper(?);";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + termo + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CadastroProdutos p = new CadastroProdutos();
                p.setId(rs.getInt("idProduto"));
                p.setProduto(rs.getString("nomeProdutos"));
                p.setValor(rs.getDouble("preco"));
                p.setFornecedor(rs.getString("fornecedor"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}




