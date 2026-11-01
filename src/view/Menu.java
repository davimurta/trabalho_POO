package view;

import dao.Conexao;

public class Menu {

    public void exibir() {
        Conexao.criarTabelas();
        int opcao;
        do {
            System.out.println("\n===== MENU INICIAL =====");
            System.out.println("1-Gerenciar ALUNOS");
            System.out.println("2-Gerenciar DISCIPLINAS");
            System.out.println("3-Gerenciar CURSOS");
            System.out.println("4-SAIR");
            opcao = Leitor.inteiro("Opcao: ");
            switch (opcao) {
                case 1 -> new SubmenuAluno().exibir();
                case 2 -> new SubmenuDisciplina().exibir();
                case 3 -> new SubmenuCurso().exibir();
                case 4 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 4);
    }
}
