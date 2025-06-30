package DAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

import javax.swing.JOptionPane;

import Datab.Datab;
import Model.Entradas;
import Model.Relatorio;
import Model.Saidas;

public class RelatorioDAO {

    private Connection conecta;

    public List<Entradas> listarEntrada(int id){
        List<Entradas> lista = new ArrayList<>();

        String sql = "SELECT entrada.*, produto.nomeProdutos as nomeProdutos, produto.nomeProdutos, concat(entrada.id_Produtos, ' - ', produto.nomeProdutos) as idNomeProduto FROM entrada " +
                "LEFT JOIN produto on entrada.id_Produtos = produto.idProduto ";

        if(id != 0) {
          sql = sql + "WHERE produto.idProduto = ?";
        }

        sql = sql + ";";
                  

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(id != 0) { stmt.setInt(1, id); }
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Entradas s = new Entradas();
                Date data = rs.getDate("dataEntrada");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                s.setIdProduto(rs.getInt("id_Produtos"));
                s.setProduto(rs.getString("nomeProdutos"));
                s.setQuantidade(rs.getInt("quantidade"));
                s.setFornecedor(rs.getString("fornecedor"));
                s.setDataEntrada(sdf.format(data));
                lista.add(s);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Saidas> listarSaida(int id){
        List<Saidas> lista = new ArrayList<>();

        String sql = "SELECT saida.*, produto.nomeProdutos as nomeProdutos, produto.nomeProdutos, concat(saida.id_Produtos, ' - ', produto.nomeProdutos) as idNomeProduto FROM saida " +
                "LEFT JOIN produto on saida.id_Produtos = produto.idProduto ";

        if(id != 0) {
          sql = sql + "WHERE produto.idProduto = ?";
        }

        sql = sql + ";";
                  

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(id != 0) { stmt.setInt(1, id); }
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Saidas s = new Saidas();
                Date data = rs.getDate("dataSaida");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                s.setIdProduto(rs.getInt("id_Produtos"));
                s.setProduto(rs.getString("nomeProdutos"));
                s.setQuantidade(rs.getInt("quantidade"));
                s.setDestinatário(rs.getString("destinatario"));
                s.setDataSaida(sdf.format(data));
                lista.add(s);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}




