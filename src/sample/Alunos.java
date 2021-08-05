package sample;

public class Alunos {

    private Integer id;
    private Integer matricula;
    private String nome;
    private Integer idade;

    public Alunos(Integer id, Integer matricula, String nome, Integer idade) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }
}
