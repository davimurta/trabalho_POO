package dao;

import model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private CursoDAO cursoDAO = new CursoDAO();

    public void inserir(Aluno a) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("INSERT INTO aluno VALUES (?, ?, ?, ?)")) {
            ps.setString(1, a.getMatricula());
            ps.setString(2, a.getNome());
            ps.setInt(3, a.getIdade());
            ps.setString(4, a.getCurso().getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        List<String[]> linhas = new ArrayList<>();
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM aluno");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                linhas.add(new String[]{rs.getString("matricula"), rs.getString("nome"),
                        String.valueOf(rs.getInt("idade")), rs.getString("codigo_curso")});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (String[] l : linhas) {
            alunos.add(new Aluno(l[0], l[1], Integer.parseInt(l[2]), cursoDAO.buscar(l[3])));
        }
        return alunos;
    }

    public Aluno buscar(String matricula) {
        for (Aluno a : listar()) {
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    public void atualizar(Aluno a) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("UPDATE aluno SET nome = ?, idade = ?, codigo_curso = ? WHERE matricula = ?")) {
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getIdade());
            ps.setString(3, a.getCurso().getCodigo());
            ps.setString(4, a.getMatricula());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remover(String matricula) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("DELETE FROM aluno WHERE matricula = ?")) {
            ps.setString(1, matricula);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
