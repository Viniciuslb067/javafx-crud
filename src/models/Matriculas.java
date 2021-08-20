package models;

public class Matriculas {

    private String nome;
    private String disciplina;

    public Matriculas(String nome, String disciplina) {
        this.nome = nome;
        this.disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public String getDisciplina() {
        return disciplina;
    }
}
