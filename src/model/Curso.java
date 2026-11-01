package model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String codigo;
    private String nome;
    private String turno;
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Curso(String codigo, String nome, String turno) {
        this.codigo = codigo;
        this.nome = nome;
        this.turno = turno;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(List<Disciplina> disciplinas) { this.disciplinas = disciplinas; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Curso: ").append(codigo).append(" - ").append(nome).append(" (").append(turno).append(")");
        for (Disciplina d : disciplinas) {
            sb.append("\n   ").append(d);
        }
        return sb.toString();
    }
}
