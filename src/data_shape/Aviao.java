package data_shape;

import java.util.ArrayList;
import java.util.List;

public class Aviao {
    private String modelo;
    private Long capacidade;

    public String toString(){
        return modelo + " => " + capacidade;
    }

    public Aviao(String modelo, Long capacidade) {
        this.modelo = modelo;
        this.capacidade = capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Long capacidade) {
        this.capacidade = capacidade;
    }
}
