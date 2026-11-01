package model;

public class Aluno {
    private String matricula;
    private String nome;
    private int idade;
    private Curso curso;

    public Aluno(String matricula, String nome, int idade, Curso curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    @Override
    public String toString() {
        return "Aluno: " + matricula + " - " + nome + ", " + idade + " anos\n " + curso;
    }
}
