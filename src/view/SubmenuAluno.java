package view;

import controller.AlunoController;
import controller.CursoController;
import model.Aluno;
import model.Curso;

public class SubmenuAluno {
    private AlunoController controller = new AlunoController();
    private CursoController cursoController = new CursoController();

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== ALUNOS =====");
            System.out.println("1-Cadastrar ALUNO");
            System.out.println("2-Consultar ALUNO");
            System.out.println("3-Remover ALUNO");
            System.out.println("4-Atualizar ALUNO");
            System.out.println("5-Voltar ao MENU INICIAL");
            opcao = Leitor.inteiro("Opcao: ");
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> consultar();
                case 3 -> remover();
                case 4 -> atualizar();
                case 5 -> { }
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 5);
    }

    private Curso escolherCurso() {
        System.out.println("Cursos disponiveis:");
        for (Curso c : cursoController.listar()) {
            System.out.println(c.getCodigo() + " - " + c.getNome());
        }
        return cursoController.buscar(Leitor.texto("Codigo do curso: "));
    }

    private void cadastrar() {
        if (cursoController.listar().isEmpty()) {
            System.out.println("Cadastre um curso primeiro.");
            return;
        }
        String matricula = Leitor.texto("Matricula: ");
        String nome = Leitor.texto("Nome: ");
        int idade = Leitor.inteiro("Idade: ");
        Curso curso = escolherCurso();
        if (curso == null) {
            System.out.println("Curso nao encontrado.");
            return;
        }
        System.out.println(controller.cadastrar(new Aluno(matricula, nome, idade, curso)));
    }

    private void consultar() {
        if (controller.listar().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        }
        for (Aluno a : controller.listar()) {
            System.out.println(a);
        }
    }

    private void remover() {
        String matricula = Leitor.texto("Matricula: ");
        System.out.println(controller.remover(matricula));
    }

    private void atualizar() {
        Aluno a = controller.buscar(Leitor.texto("Matricula: "));
        if (a == null) {
            System.out.println("Aluno nao encontrado.");
            return;
        }
        a.setNome(Leitor.texto("Novo nome: "));
        a.setIdade(Leitor.inteiro("Nova idade: "));
        Curso curso = escolherCurso();
        if (curso == null) {
            System.out.println("Curso nao encontrado.");
            return;
        }
        a.setCurso(curso);
        System.out.println(controller.atualizar(a));
    }
}
