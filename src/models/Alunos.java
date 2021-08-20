package models;

public class Alunos {

    private Integer id;
    private Integer matricula;
    private String nome;
    private Integer idade;
    private Long telefone;
    private Long telefonePais;

    public Alunos(Integer id, Integer matricula, String nome, Integer idade, Long telefone, Long telefonePais) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.telefonePais = telefonePais;
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

    public Long getTelefone() {
        return telefone;
    }

    public Long getTelefonePais() {
        return telefonePais;
    }
}
