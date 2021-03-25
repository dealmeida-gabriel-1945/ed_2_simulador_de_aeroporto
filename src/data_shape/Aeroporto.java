package data_shape;

import java.util.HashMap;
import java.util.List;

public class Aeroporto {
    private String abreviacao;
    private String timezone;
    private Long coordenadaX;
    private Long coordenadaY;
    private String local;
    private HashMap<String, Aeroporto> destinos = new HashMap<>();


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(abreviacao);
        sb.append(" ");
        sb.append(timezone);
        sb.append(" ");
        sb.append(coordenadaX);
        sb.append("x");
        sb.append(coordenadaY);
        sb.append(" ");
        sb.append(local);
        sb.append("\n------ Destinos:");
        if(destinos.isEmpty()) sb.append("\n\t---> Nenhum Destino.");
        destinos.forEach((s, aeroporto) -> sb.append("\n\t---> " + aeroporto.getAbreviacao()));
        sb.append("\n~~~~~~~~~~~~~~~~~~~~\n");
        return sb.toString();
    }


    public Aeroporto(){}

    public Aeroporto(String abreviacao, String timezone, Long coordenadaX, Long coordenadaY, String local) {
        this.abreviacao = abreviacao;
        this.timezone = timezone;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.local = local;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public HashMap<String, Aeroporto> getDestinos() {
        return destinos;
    }

    public void setDestinos(HashMap<String, Aeroporto> destinos) {
        this.destinos = destinos;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Long getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Long coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Long getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Long coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
