package view;

import controller.CursoController;
import model.Curso;
import model.Disciplina;

public class SubmenuCurso {
    private CursoController controller = new CursoController();

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== CURSOS =====");
            System.out.println("1-Cadastrar CURSO");
            System.out.println("2-Consultar CURSO");
            System.out.println("3-Remover CURSO");
            System.out.println("4-Atualizar CURSO");
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
        String codigo = Leitor.texto("Codigo do curso: ");
        String nome = Leitor.texto("Nome: ");
        String turno = Leitor.texto("Turno: ");
        System.out.println("Cadastre a primeira disciplina do curso:");
        String codDisc = Leitor.texto("Codigo da disciplina: ");
        String nomeDisc = Leitor.texto("Nome da disciplina: ");
        int carga = Leitor.inteiro("Carga horaria: ");
        Curso curso = new Curso(codigo, nome, turno);
        Disciplina disciplina = new Disciplina(codDisc, nomeDisc, carga, codigo);
        System.out.println(controller.cadastrar(curso, disciplina));
    }

    private void consultar() {
        if (controller.listar().isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        }
        for (Curso c : controller.listar()) {
            System.out.println(c);
        }
    }

    private void remover() {
        String codigo = Leitor.texto("Codigo do curso: ");
        System.out.println(controller.remover(codigo));
    }

    private void atualizar() {
        Curso curso = controller.buscar(Leitor.texto("Codigo do curso: "));
        if (curso == null) {
            System.out.println("Curso nao encontrado.");
            return;
        }
        curso.setNome(Leitor.texto("Novo nome: "));
        curso.setTurno(Leitor.texto("Novo turno: "));
        System.out.println(controller.atualizar(curso));
    }
}
