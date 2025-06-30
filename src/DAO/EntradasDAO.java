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

public class EntradasDAO {

    private Connection conecta;

    public List<String> carregaProdutos(){
        List<String> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto;";
        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String produto = "";
                produto = produto + Integer.toString(rs.getInt("idProduto"));
                produto = produto + " - " + rs.getString("nomeProdutos");
                lista.add(produto);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void cadastrarEntrada(Entradas obj) throws ParseException{

        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(obj.getDataEntrada());
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());


            Connection conn = Datab.getConnection();
            String sql = "insert into entrada (id_Produtos, quantidade, dataEntrada, fornecedor) values(?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt   (1,Integer.parseInt(obj.getProduto()));
            stmt.setInt   (2,obj.getQuantidade());
            stmt.setDate  (3,dataSQL);
            stmt.setString(4,obj.getFornecedor());

            stmt.execute();

            stmt.close();
        } catch (SQLException erro) {

            throw new RuntimeException(erro);
        }
    }

    public List<Entradas> listarEntradas(){
        List<Entradas> lista = new ArrayList<>();
        String sql = "SELECT entrada.*, produto.IdProduto, produto.nomeProdutos FROM entrada " +
                        "LEFT JOIN produto on entrada.id_Produtos = produto.idProduto;";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Entradas e = new Entradas();
                String produto = String.valueOf(rs.getInt("IdProduto")) + " - " + rs.getString("nomeProdutos");
                
                Date data = rs.getDate("dataEntrada");
                SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
                e.setId(rs.getInt("idEntrada"));
                e.setProduto(produto);
                e.setDataEntrada(dataFormatada.format(data));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setFornecedor(rs.getString("fornecedor"));

                lista.add(e);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void excluirEntrada(int id){
        try {
            Connection conn = Datab.getConnection();
            String sql = "DELETE FROM entrada WHERE idEntrada = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public List<Entradas> pesquisarEntrada(int id){
        List<Entradas> lista = new ArrayList<>();

        String sql = "SELECT entrada.*, produto.nomeProdutos as nomeProdutos, concat(entrada.id_Produtos, ' - ', produto.nomeProdutos) as idNomeProduto FROM entrada " +
                "LEFT JOIN produto on entrada.id_Produtos = produto.idProduto " +
                "WHERE idEntrada = ?;";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Entradas s = new Entradas();
                Date data = rs.getDate("dataEntrada");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                s.setProduto(rs.getString("idNomeProduto"));
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

    public void salvarEdicao(int id, Entradas obj){
        try {
            Connection conn = Datab.getConnection();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(obj.getDataEntrada());
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());

            String sql = "UPDATE entrada SET Id_Produtos = ?, quantidade = ?, dataEntrada = ?, fornecedor = ? WHERE idEntrada = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt   (1,Integer.parseInt(obj.getProduto()));
            stmt.setInt   (2,obj.getQuantidade());
            stmt.setDate  (3,dataSQL);
            stmt.setString(4,obj.getFornecedor());
            stmt.setInt   (5,id);

            stmt.execute();

            stmt.close();  
        } catch (Exception erro) {
                throw new RuntimeException(erro);
        }
    }
}




