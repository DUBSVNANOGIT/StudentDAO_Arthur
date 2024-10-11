package br.dev.joaquim.StudentApp.entities;

import java.util.*;


public class Curso {
    private int cod;
    private String nome;
    private String nomeProfessor;
    private String periodo;

    public Curso(int cod, String nome, String nomeProfessor, String periodo) {
    this.nome = nome;
    this.nomeProfessor = nomeProfessor;
    this.periodo = periodo;
}

public int getCod() {
    return this.cod;
}

public String getNome() {
    return this.nome;
}

public void setNome(String nome) {
    this.nome = nome;
}


public String getNomeProfessor() {
    return this.nomeProfessor;
}

public void setnomeProfessor(String nomeProfessor) {
    this.nomeProfessor = nomeProfessor;
}

public String getPeriodo() {
    return this.periodo;
}

public void setPeriodo(String periodo) {
    this.periodo = periodo;
}

@Override
public String toString() {
    return "Curso [cod=" + cod + ", nome=" + nome + ", nomeProfessor=" + nomeProfessor + ", periodo=" + periodo + "]";
}

}