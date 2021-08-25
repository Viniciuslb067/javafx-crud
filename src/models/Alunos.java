package models;

public class Alunos {

    private final Integer id;
    private final Integer matricula;
    private final String nome;
    private final Integer idade;
    private final Long telefone;
    private final Long telefonePais;

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
