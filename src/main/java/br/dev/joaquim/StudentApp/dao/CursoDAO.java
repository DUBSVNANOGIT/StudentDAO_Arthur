package br.dev.joaquim.StudentApp.dao;

import br.dev.joaquim.StudentApp.entities.Curso;
import java.util.*;

public interface CursoDAO {
    public boolean create(Curso curso);
    public List<Curso> findAll();
    public Curso FindById(int id);
    public boolean update(Curso curso);
    public boolean delete(int id);
}
