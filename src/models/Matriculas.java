package models;

public class Matriculas {

    private final String nome;
    private final String disciplina;

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
