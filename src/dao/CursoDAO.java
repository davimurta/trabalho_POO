package dao;

import model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    public void inserir(Curso curso) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("INSERT INTO curso VALUES (?, ?, ?)")) {
            ps.setString(1, curso.getCodigo());
            ps.setString(2, curso.getNome());
            ps.setString(3, curso.getTurno());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM curso");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Curso curso = new Curso(rs.getString("codigo"), rs.getString("nome"), rs.getString("turno"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Curso curso : cursos) {
            curso.setDisciplinas(disciplinaDAO.listarPorCurso(curso.getCodigo()));
        }
        return cursos;
    }

    public Curso buscar(String codigo) {
        for (Curso curso : listar()) {
            if (curso.getCodigo().equals(codigo)) {
                return curso;
            }
        }
        return null;
    }

    public void atualizar(Curso curso) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("UPDATE curso SET nome = ?, turno = ? WHERE codigo = ?")) {
            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getTurno());
            ps.setString(3, curso.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remover(String codigo) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps1 = c.prepareStatement("DELETE FROM disciplina WHERE codigo_curso = ?");
             PreparedStatement ps2 = c.prepareStatement("DELETE FROM curso WHERE codigo = ?")) {
            ps1.setString(1, codigo);
            ps1.executeUpdate();
            ps2.setString(1, codigo);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
