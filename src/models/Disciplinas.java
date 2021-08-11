package models;

public class Disciplinas {

    private Integer id;
    private String nome;
    private String tipo;
    private Integer cargaHorario;

    public Disciplinas(Integer id, String nome, String tipo, Integer cargaHorario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cargaHorario = cargaHorario;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getCargaHorario() {
        return cargaHorario;
    }
}
