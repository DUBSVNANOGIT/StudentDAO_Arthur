package br.dev.joaquim.StudentApp.ihm;

import java.util.Scanner;

import br.dev.joaquim.StudentApp.dao.H2CursoDAO;

public class CursoIHM {

    private H2CursoDAO cd;

    public CursoIHM(H2CursoDAO cd) {
        this.cd = cd;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Curso Menu ===");
            System.out.println("1. Add Curso");
            System.out.println("2. View All Cursos");
            System.out.println("3. Update Curso");
            System.out.println("4. Delete Cu");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student RA: ");
        int ra = scanner.nextInt();

        Student student = new Student();
        student.setName(name);
        student.setRa(ra);

        studentDAO.create(student);
        System.out.println("Student added successfully.");
    }

    private void viewAllStudents() {
        System.out.println("=== List of Students ===");
        for (Student student : studentDAO.findAll()) {
            System.out.println(student);
        }
    }

    private void updateStudent(Scanner scanner) {
        System.out.print("Enter student RA to update: ");
        int ra = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = studentDAO.findByRa(ra);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        student.setName(name);

        studentDAO.update(student);
        System.out.println("Student updated successfully.");
    }

    private void deleteStudent(Scanner scanner) {
        System.out.print("Enter student RA to delete: ");
        int ra = scanner.nextInt();
        studentDAO.delete(ra);
        System.out.println("Student deleted successfully.");
    }
    
}
