package DAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

import javax.swing.JOptionPane;

import Datab.Datab;
import Model.Saidas;

public class SaidaDAO {

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

    public void cadastrarSaida(Saidas obj) throws ParseException{

        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(obj.getDataSaida());
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());


            Connection conn = Datab.getConnection();
            String sql = "insert into saida (id_Produtos, quantidade, dataSaida, destinatario) values(?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt   (1,Integer.parseInt(obj.getProduto()));
            stmt.setInt   (2,obj.getQuantidade());
            stmt.setDate  (3,dataSQL);
            stmt.setString(4,obj.getDestinatário());

            stmt.execute();

            stmt.close();
        } catch (SQLException erro) {

            throw new RuntimeException(erro);
        }
    }

    public List<Saidas> listarSaidas(){
        List<Saidas> lista = new ArrayList<>();
        String sql = "SELECT saida.*, produto.IdProduto, produto.nomeProdutos FROM saida " +
                        "LEFT JOIN produto on saida.id_Produtos = produto.idProduto;";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Saidas e = new Saidas();
                String produto = String.valueOf(rs.getInt("IdProduto")) + " - " + rs.getString("nomeProdutos");
                
                Date data = rs.getDate("dataSaida");
                SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
                e.setId(rs.getInt("idSaida"));
                e.setProduto(produto);
                e.setDataSaida(dataFormatada.format(data));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setDestinatário(rs.getString("destinatario"));

                lista.add(e);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void excluirSaida(int id){
        try {
            Connection conn = Datab.getConnection();
            String sql = "DELETE FROM saida WHERE idSaida = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public List<Saidas> pesquisarSaida(int id){
        List<Saidas> lista = new ArrayList<>();

        String sql = "SELECT saida.*, produto.nomeProdutos as nomeProdutos, concat(saida.id_Produtos, ' - ', produto.nomeProdutos) as idNomeProduto FROM saida " +
                "LEFT JOIN produto on saida.id_Produtos = produto.idProduto " +
                "WHERE idSaida = ?;";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Saidas s = new Saidas();
                Date data = rs.getDate("dataSaida");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                s.setProduto(rs.getString("idNomeProduto"));
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

    public void salvarEdicao(int id, Saidas obj){
        try {
            Connection conn = Datab.getConnection();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataUtil = formato.parse(obj.getDataSaida());
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());

            String sql = "UPDATE saida SET Id_Produtos = ?, quantidade = ?, DataSaida = ?, destinatario = ? WHERE idSaida = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt   (1,Integer.parseInt(obj.getProduto()));
            stmt.setInt   (2,obj.getQuantidade());
            stmt.setDate  (3,dataSQL);
            stmt.setString(4,obj.getDestinatário());
            stmt.setInt   (5, id);

            stmt.execute();

            stmt.close();  
        } catch (Exception erro) {
                throw new RuntimeException(erro);
        }
    }
}




