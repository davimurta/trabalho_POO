package controller;

import dao.AlunoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import model.Aluno;
import model.Curso;
import model.Disciplina;

import java.util.List;

public class CursoController {
    private CursoDAO cursoDAO = new CursoDAO();
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private AlunoDAO alunoDAO = new AlunoDAO();

    public String cadastrar(Curso curso, Disciplina primeiraDisciplina) {
        if (cursoDAO.buscar(curso.getCodigo()) != null) {
            return "Ja existe um curso com esse codigo.";
        }
        if (disciplinaDAO.buscar(primeiraDisciplina.getCodigo()) != null) {
            return "Ja existe uma disciplina com esse codigo.";
        }
        cursoDAO.inserir(curso);
        disciplinaDAO.inserir(primeiraDisciplina);
        return "Curso cadastrado com sucesso.";
    }

    public List<Curso> listar() {
        return cursoDAO.listar();
    }

    public Curso buscar(String codigo) {
        return cursoDAO.buscar(codigo);
    }

    public String atualizar(Curso curso) {
        cursoDAO.atualizar(curso);
        return "Curso atualizado com sucesso.";
    }

    public String remover(String codigo) {
        if (cursoDAO.buscar(codigo) == null) {
            return "Curso nao encontrado.";
        }
        for (Aluno a : alunoDAO.listar()) {
            if (a.getCurso() != null && a.getCurso().getCodigo().equals(codigo)) {
                return "Nao e possivel remover: existem alunos nesse curso.";
            }
        }
        cursoDAO.remover(codigo);
        return "Curso removido com sucesso.";
    }
}
