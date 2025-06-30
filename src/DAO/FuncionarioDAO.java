package DAO;

import java.sql.*;
import java.util.*;


import javax.swing.JOptionPane;

import Datab.Datab;
import Model.Funcionario;

public class FuncionarioDAO {

  private Connection conecta;

    public void cadastrarFuncionario(Funcionario obj){

        try {
            Connection conn = Datab.getConnection();

            String sql = "insert into funcionarios (nomeFuncionario, cargo, login, senha) values(?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getCargo());
            stmt.setString(3,obj.getLogin());
            stmt.setString(4,obj.getSenha());

            stmt.execute();

            stmt.close();
        } catch (SQLException erro) {

            throw new RuntimeException(erro);
        }
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios;";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(Integer.toString(rs.getInt("idFuncionarios")));
                f.setNome(rs.getString("nomeFuncionario"));
                f.setCargo(rs.getString("cargo"));
                lista.add(f);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public List<Funcionario> pesquisarFuncionarios(String termo) {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT idFuncionarios, nomeFuncionario, cargo FROM funcionarios WHERE upper(nomeFuncionario) like upper(?);";

        try {
            Connection conn = Datab.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + termo + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(Integer.toString(rs.getInt("idFuncionarios")));
                f.setNome(rs.getString("nomeFuncionario"));
                f.setCargo(rs.getString("cargo"));
                lista.add(f);
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void excluirProduto(int Id){

        try {
            Connection conn = Datab.getConnection();
            String sql = "DELETE FROM funcionarios WHERE idFuncionarios = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Id);

            stmt.execute();
            stmt.close();
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public List<Funcionario> pesquisarAtualizar(String id){
        List<Funcionario> lista = new ArrayList<>();
        try {
            Connection conn = Datab.getConnection();

            String sql = "SELECT * FROM funcionarios WHERE idFuncionarios = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(Integer.toString(rs.getInt("idFuncionarios")));
                f.setNome(rs.getString("nomeFuncionario"));
                f.setCargo(rs.getString("cargo"));
                f.setLogin(rs.getString("login"));
                f.setSenha(rs.getString("senha"));
                lista.add(f);
            }

            stmt.close();
            rs.close();

        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }

    public void salvarEdicaoFuncionarios(int idEdicao, Funcionario obj){
        try {
            Connection conn = Datab.getConnection();

            String sql = "UPDATE funcionarios SET nomeFuncionario = ?, cargo = ?, login = ?, senha = ? WHERE idFuncionarios = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getCargo());
            stmt.setString(3,obj.getLogin());
            stmt.setString(4,obj.getSenha());
            stmt.setInt   (5, idEdicao);

            stmt.execute();

            stmt.close();  
        } catch (Exception erro) {
                throw new RuntimeException(erro);
        }
    }

}
