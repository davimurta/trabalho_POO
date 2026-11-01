package dao;

import model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public void inserir(Disciplina d) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("INSERT INTO disciplina VALUES (?, ?, ?, ?)")) {
            ps.setString(1, d.getCodigo());
            ps.setString(2, d.getNome());
            ps.setInt(3, d.getCargaHoraria());
            ps.setString(4, d.getCodigoCurso());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Disciplina> listar() {
        return consultar("SELECT * FROM disciplina", null);
    }

    public List<Disciplina> listarPorCurso(String codigoCurso) {
        return consultar("SELECT * FROM disciplina WHERE codigo_curso = ?", codigoCurso);
    }

    public Disciplina buscar(String codigo) {
        for (Disciplina d : listar()) {
            if (d.getCodigo().equals(codigo)) {
                return d;
            }
        }
        return null;
    }

    public void atualizar(Disciplina d) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("UPDATE disciplina SET nome = ?, carga_horaria = ? WHERE codigo = ?")) {
            ps.setString(1, d.getNome());
            ps.setInt(2, d.getCargaHoraria());
            ps.setString(3, d.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remover(String codigo) {
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement("DELETE FROM disciplina WHERE codigo = ?")) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Disciplina> consultar(String sql, String parametro) {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (Connection c = Conexao.getConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            if (parametro != null) {
                ps.setString(1, parametro);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    disciplinas.add(new Disciplina(rs.getString("codigo"), rs.getString("nome"),
                            rs.getInt("carga_horaria"), rs.getString("codigo_curso")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disciplinas;
    }
}
