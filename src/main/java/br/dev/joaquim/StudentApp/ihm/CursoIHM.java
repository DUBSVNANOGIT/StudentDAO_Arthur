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
            System.out.println("1. Add Curso");
            System.out.println("2. View All Cursos");
            System.out.println("3. Update Curso");
            System.out.println("4. Delete Curso");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
        System.out.print("Enter curso code: ");
        int cod = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter curso name: ");
        String nome = scanner.nextLine();
        System.out.print("Enter professor's name: ");
        String nomeProfessor = scanner.nextLine();
        System.out.print("Enter period: ");
        String periodo = scanner.nextLine();

        Curso curso = new Curso(cod, nome, nomeProfessor, periodo);
        cursoDAO.create(curso);
        System.out.println("Curso added successfully.");
    }

    private void viewAllCursos() {
        System.out.println("=== List of Cursos ===");
        for (Curso curso : cursoDAO.findAll()) {
            System.out.println(curso);
        }
    }

    private void updateCurso(Scanner scanner) {
        System.out.print("Enter curso code to update: ");
        int cod = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Curso curso = cursoDAO.findByCod(cod);
        if (curso == null) {
            System.out.println("Curso not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String nome = scanner.nextLine();
        System.out.print("Enter new professor's name: ");
        String nomeProfessor = scanner.nextLine();
        System.out.print("Enter new period: ");
        String periodo = scanner.nextLine();

        curso.setNome(nome);
        curso.setNomeProfessor(nomeProfessor);
        curso.setPeriodo(periodo);

        cursoDAO.update(curso);
        System.out.println("Curso updated successfully.");
    }

    private void deleteCurso(Scanner scanner) {
        System.out.print("Enter curso code to delete: ");
        int cod = scanner.nextInt();
        cursoDAO.delete(cod);
        System.out.println("Curso deleted successfully.");
    }
}
