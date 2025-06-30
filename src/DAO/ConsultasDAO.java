package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Datab.Datab;
import Model.Consultas;

public class ConsultasDAO {

    private Connection conecta;

    // Construtor
    public ConsultasDAO() throws SQLException {
        this.conecta = Datab.getConnection();
        if (this.conecta != null) {
            System.out.println(" Conexão estabelecida com sucesso!");
        } else {
            System.out.println(" Falha ao conectar ao banco de dados!");
        }
    }

 public List<Consultas> listarEstoqueCalculado() {
    List<Consultas> lista = new ArrayList<>();

    String sql = "SELECT " +
                 "p.idProduto, p.nomeProdutos, p.categoria,  " +
                 "COALESCE(SUM(e.quantidade), 0) AS totalEntradas, " +
                 "COALESCE(SUM(s.quantidade), 0) AS totalSaidas, " +
                 "(COALESCE(SUM(e.quantidade), 0) - COALESCE(SUM(s.quantidade), 0)) AS estoqueAtual " +
                 "FROM produto p " +
                 "LEFT JOIN entrada e ON p.idProduto = e.id_Produtos " +
                 "LEFT JOIN saida s ON p.idProduto = s.id_Produtos " +
                 "GROUP BY p.idProduto, p.nomeProdutos, p.categoria";

    try {
        Connection conn = Datab.getConnection(); // Certifique-se que sua conexão funciona
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Consultas c = new Consultas();
            c.setIdProduto(rs.getInt("idProduto"));
            c.setNomeProduto(rs.getString("nomeProdutos"));
            c.setCategoria(rs.getString("categoria"));
            c.setTotalEntradas(rs.getInt("totalEntradas"));
            c.setTotalSaidas(rs.getInt("totalSaidas"));
            c.setEstoqueAtual(rs.getInt("estoqueAtual"));
            lista.add(c);
        }

        stmt.close();
        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao consultar estoque: " + e);
    }

    return lista;
}


}
