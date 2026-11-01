package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static final String URL = "jdbc:sqlite:faculdade.db";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void criarTabelas() {
        try (Connection c = getConexao(); Statement st = c.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS curso (codigo TEXT PRIMARY KEY, nome TEXT, turno TEXT)");
            st.execute("CREATE TABLE IF NOT EXISTS disciplina (codigo TEXT PRIMARY KEY, nome TEXT, carga_horaria INTEGER, codigo_curso TEXT)");
            st.execute("CREATE TABLE IF NOT EXISTS aluno (matricula TEXT PRIMARY KEY, nome TEXT, idade INTEGER, codigo_curso TEXT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
