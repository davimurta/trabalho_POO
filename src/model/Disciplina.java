package model;

public class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private String codigoCurso;

    public Disciplina(String codigo, String nome, int cargaHoraria, String codigoCurso) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.codigoCurso = codigoCurso;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public String getCodigoCurso() { return codigoCurso; }

    @Override
    public String toString() {
        return "Disciplina: " + codigo + " - " + nome + " (" + cargaHoraria + "h) | Curso: " + codigoCurso;
    }
}
