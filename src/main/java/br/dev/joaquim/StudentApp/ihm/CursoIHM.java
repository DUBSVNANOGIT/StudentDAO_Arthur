package br.dev.joaquim.StudentApp.ihm;

import java.util.Scanner;
import br.dev.joaquim.StudentApp.dao.H2CursoDAO;
import br.dev.joaquim.StudentApp.entities.Curso;

public class CursoIHM {

    private H2CursoDAO cursoDAO;

    public CursoIHM(H2CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Curso Menu ===");
            System.out.println("1. Adicionar Curso");
            System.out.println("2. Ver todos os Cursos");
            System.out.println("3. Atualizar Curso");
            System.out.println("4. Deletar Curso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    addCurso(scanner);
                    break;
                case 2:
                    viewAllCursos();
                    break;
                case 3:
                    updateCurso(scanner);
                    break;
                case 4:
                    deleteCurso(scanner);
                    break;
                case 0:
                    System.out.println("Saíndo...");
                    break;
                default:
                    System.out.println("Opção Inválida.");
            }
        }

        scanner.close();
    }

    private void addCurso(Scanner scanner) {
        System.out.print("Insira o código do curso: ");
        int cod = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Insira o nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Insira o nome do professor do curso: ");
        String nomeProfessor = scanner.nextLine();
        System.out.print("Insira o período do curso: ");
        String periodo = scanner.nextLine();

        Curso curso = new Curso(cod, nome, nomeProfessor, periodo);
        cursoDAO.create(curso);
        System.out.println("Curso adicionado com sucesso!");
    }

    private void viewAllCursos() {
        System.out.println("=== Lista de Cursos ===");
        for (Curso curso : cursoDAO.findAll()) {
            System.out.println(curso.toString());
        }
    }

    private void updateCurso(Scanner scanner) {
        System.out.print("Informe o código do curso a ser atualizado: ");
        int cod = scanner.nextInt();
        scanner.nextLine(); 

        Curso curso = cursoDAO.FindById(cod);
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }
        cursoDAO.update(curso);
        System.out.println("Curso atualizado com sucesso.");
    }

    private void deleteCurso(Scanner scanner) {
        System.out.print("Insira o código do curso a ser deletado: ");
        int cod = scanner.nextInt();
        cursoDAO.delete(cod);
        System.out.println("Curso deletado!");
    }
}
