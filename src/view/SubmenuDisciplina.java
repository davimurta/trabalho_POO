package view;

import controller.CursoController;
import controller.DisciplinaController;
import model.Curso;
import model.Disciplina;

public class SubmenuDisciplina {
    private DisciplinaController controller = new DisciplinaController();
    private CursoController cursoController = new CursoController();

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== DISCIPLINAS =====");
            System.out.println("1-Cadastrar DISCIPLINA");
            System.out.println("2-Consultar DISCIPLINA");
            System.out.println("3-Remover DISCIPLINA");
            System.out.println("4-Atualizar DISCIPLINA");
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

    private void cadastrar() {
        if (cursoController.listar().isEmpty()) {
            System.out.println("Cadastre um curso primeiro.");
            return;
        }
        System.out.println("Cursos disponiveis:");
        for (Curso c : cursoController.listar()) {
            System.out.println(c.getCodigo() + " - " + c.getNome());
        }
        String codigoCurso = Leitor.texto("Codigo do curso: ");
        String codigo = Leitor.texto("Codigo da disciplina: ");
        String nome = Leitor.texto("Nome: ");
        int carga = Leitor.inteiro("Carga horaria: ");
        System.out.println(controller.cadastrar(new Disciplina(codigo, nome, carga, codigoCurso)));
    }

    private void consultar() {
        if (controller.listar().isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        }
        for (Disciplina d : controller.listar()) {
            System.out.println(d);
        }
    }

    private void remover() {
        String codigo = Leitor.texto("Codigo da disciplina: ");
        System.out.println(controller.remover(codigo));
    }

    private void atualizar() {
        Disciplina d = controller.buscar(Leitor.texto("Codigo da disciplina: "));
        if (d == null) {
            System.out.println("Disciplina nao encontrada.");
            return;
        }
        d.setNome(Leitor.texto("Novo nome: "));
        d.setCargaHoraria(Leitor.inteiro("Nova carga horaria: "));
        System.out.println(controller.atualizar(d));
    }
}
