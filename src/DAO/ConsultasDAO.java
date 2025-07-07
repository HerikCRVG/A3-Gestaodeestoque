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
             "p.idProduto, p.nomeProdutos, p.categoria, " +
             "COALESCE((SELECT SUM(e.quantidade) FROM entrada e WHERE e.id_Produtos = p.idProduto), 0) AS totalEntradas, " +
             "COALESCE((SELECT SUM(s.quantidade) FROM saida s WHERE s.id_Produtos = p.idProduto), 0) AS totalSaidas, " +
             "(COALESCE((SELECT SUM(e.quantidade) FROM entrada e WHERE e.id_Produtos = p.idProduto), 0) - " +
             " COALESCE((SELECT SUM(s.quantidade) FROM saida s WHERE s.id_Produtos = p.idProduto), 0)) AS estoqueAtual " +
             "FROM produto p";

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
