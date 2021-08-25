package models;

public class Disciplinas {

    private final Integer id;
    private final String nome;
    private final String tipo;
    private final Integer cargaHorario;
    private final Integer periodo;

    public Disciplinas(Integer id, String nome, String tipo, Integer cargaHorario, Integer periodo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cargaHorario = cargaHorario;
        this.periodo = periodo;
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

    public Integer getPeriodo() {
        return periodo;
    }
}
