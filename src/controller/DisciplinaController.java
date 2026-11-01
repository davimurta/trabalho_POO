package controller;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import model.Disciplina;

import java.util.List;

public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private CursoDAO cursoDAO = new CursoDAO();

    public String cadastrar(Disciplina d) {
        if (cursoDAO.buscar(d.getCodigoCurso()) == null) {
            return "Curso nao encontrado.";
        }
        if (disciplinaDAO.buscar(d.getCodigo()) != null) {
            return "Ja existe uma disciplina com esse codigo.";
        }
        disciplinaDAO.inserir(d);
        return "Disciplina cadastrada com sucesso.";
    }

    public List<Disciplina> listar() {
        return disciplinaDAO.listar();
    }

    public Disciplina buscar(String codigo) {
        return disciplinaDAO.buscar(codigo);
    }

    public String atualizar(Disciplina d) {
        disciplinaDAO.atualizar(d);
        return "Disciplina atualizada com sucesso.";
    }

    public String remover(String codigo) {
        Disciplina d = disciplinaDAO.buscar(codigo);
        if (d == null) {
            return "Disciplina nao encontrada.";
        }
        if (disciplinaDAO.listarPorCurso(d.getCodigoCurso()).size() <= 1) {
            return "Nao e possivel remover: o curso precisa ter pelo menos 1 disciplina.";
        }
        disciplinaDAO.remover(codigo);
        return "Disciplina removida com sucesso.";
    }
}
