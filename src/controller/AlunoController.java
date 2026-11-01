package controller;

import dao.AlunoDAO;
import model.Aluno;

import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO = new AlunoDAO();

    public String cadastrar(Aluno a) {
        if (alunoDAO.buscar(a.getMatricula()) != null) {
            return "Ja existe um aluno com essa matricula.";
        }
        alunoDAO.inserir(a);
        return "Aluno cadastrado com sucesso.";
    }

    public List<Aluno> listar() {
        return alunoDAO.listar();
    }

    public Aluno buscar(String matricula) {
        return alunoDAO.buscar(matricula);
    }

    public String atualizar(Aluno a) {
        alunoDAO.atualizar(a);
        return "Aluno atualizado com sucesso.";
    }

    public String remover(String matricula) {
        if (alunoDAO.buscar(matricula) == null) {
            return "Aluno nao encontrado.";
        }
        alunoDAO.remover(matricula);
        return "Aluno removido com sucesso.";
    }
}
