package br.dev.joaquim.StudentApp.dao;

import br.dev.joaquim.StudentApp.entities.Curso;

import java.sql.*;
import java.util.*;
public class H2CursoDAO implements CursoDAO {
    private Connection conn;
    private String url = "jdbc:h2:file:~/data/students;";
    private String user = "root";
    private String password = "root";

    public H2CursoDAO() {
        connection();
        createTableIfNotExists();
    }

    public void connection() {
        try {
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            this.conn = null;
            System.out.println(e.getMessage());
        }
    }

    public void createTableIfNotExists() {
        try {
            String sql = "CREATE TABLE IF NOT EXIST curso(" + "cod INT PRIMARY KEY, nome VARCHAR(255), nomeProfessor VARCHAR(256), periodo VARCHAR(30)" + ");";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Problema ao criar a tabela");
        } catch (NullPointerException e) {
            System.out.println("Problema ao criar a tabela (sem conexão)");
        }
    }

    @Override
    public boolean create(Curso curso) {
        try {
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO curso VALUES (?,?,?,?)");
            pstm.setInt(1, curso.getCod());
            pstm.setString(2, curso.getNome());
            pstm.setString(3, curso.getNomeProfessor());
            pstm.setString(4, curso.getPeriodo());
            return true;
        } catch (SQLException ex) {
        System.out.println("Problema ao criar curso");
        ex.printStackTrace();
        } catch (NullPointerException ex) {
        System.out.println("Problema ao criar curso (sem conexao)");
        }
        return false;
    }

    @Override
    public List<Curso> findAll() {
        try {
            PreparedStatement pstm = conn.prepareStatement("SELECT * from curso");
            ResultSet rs = pstm.executeQuery();
            List<Curso> cursos = new ArrayList<>();
            while (rs.next()) {
                cursos.add(new Curso(rs.getInt("cod"), rs.getString("nome"), rs.getString("nomeProfessor"), rs.getString("periodo")));
            }
            return cursos;
        } catch (SQLException ex) {
        System.out.println("Problema ao buscar curso");
        ex.printStackTrace();
        } catch (NullPointerException ex) {
        System.out.println("Problema ao buscar curso (sem conexao)");
        }
        return new ArrayList<>();
       }

    @Override
    public Curso FindById(int cod) {
        try {
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM curso WHERE cod = ?");
            pstm.setInt(1, cod);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return new Curso (rs.getInt("cod"), rs.getString("nome"), rs.getString("nomeProfessor"), rs.getString("periodo"));
            }
        } catch (SQLException ex) {
        System.out.println("Problema ao buscar curso pelo COD");
        ex.printStackTrace();
        } catch (NullPointerException ex) {
        System.out.println("Problema ao buscar curso pelo COD (sem conexao)");
        }
      
        return null;
        }

    @Override
    public boolean update(Curso curso) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Novo professor: ");
        String nP = sc.nextLine();
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE curso SET cod = ? WHERE nomeProfessor = ?");
            pstm.setInt(1, curso.getCod());
            pstm.setString(2, nP);
            pstm.executeUpdate();
            sc.close();
            return true;
        } catch (SQLException ex) {
        System.out.println("Problema ao atualizar curso");
        ex.printStackTrace();
        } catch (NullPointerException ex) {
        System.out.println("Problema ao atualizar curso (sem conexao)");
        }
        return false;
    }

    @Override
    public boolean delete(int cod) {
        try {
            PreparedStatement pstm = conn.prepareStatement("DELETE curso WHERE cod = ?");
            pstm.setInt(1, cod);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
        System.out.println("Problema ao apagar estudante");
        ex.printStackTrace();
        } catch (NullPointerException ex) {
        System.out.println("Problema ao apagar estudante (sem conexao)");
        }
        return false;
        }
}
