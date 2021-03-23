package data_shape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Voo {
    private String linhaAerea;
    private String voo;
    private String aeroportoOrigem;
    private String horaPartida;
    private String aeroportoDestino;
    private String horaChegada;
    private String comida;
    private Integer paradas;
    private String tipo;
    private List<String> classes = new ArrayList<>();

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append(linhaAerea);
        sb.append(" ");
        sb.append(voo);
        sb.append(" ");
        sb.append(aeroportoOrigem);
        sb.append(" ");
        sb.append(horaChegada);
        sb.append(" ");
        sb.append(aeroportoDestino);
        sb.append(" ");
        sb.append(horaChegada);
        sb.append(" ");
        sb.append(comida);
        sb.append(" ");
        sb.append(paradas);
        sb.append(" ");
        sb.append(tipo);
        sb.append(" ");
        sb.append(classes.stream().collect(Collectors.joining(" ")));
        sb.append(" ");

        return sb.toString();
    }

    public String getAeroportoDestino() {
        return aeroportoDestino;
    }

    public void setAeroportoDestino(String aeroportoDestino) {
        this.aeroportoDestino = aeroportoDestino;
    }

    public String getLinhaAerea() {
        return linhaAerea;
    }

    public void setLinhaAerea(String linhaAerea) {
        this.linhaAerea = linhaAerea;
    }

    public String getVoo() {
        return voo;
    }

    public void setVoo(String voo) {
        this.voo = voo;
    }

    public String getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public void setAeroportoOrigem(String aeroportoOrigem) {
        this.aeroportoOrigem = aeroportoOrigem;
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comia) {
        this.comida = comia;
    }

    public Integer getParadas() {
        return paradas;
    }

    public void setParadas(Integer paradas) {
        this.paradas = paradas;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getClasses() {
        return classes;
    }
}
