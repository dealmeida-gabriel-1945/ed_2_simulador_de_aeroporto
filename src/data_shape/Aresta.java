package data_shape;

public class Aresta {
    private Aeroporto origem;
    private Aeroporto destino;
    private Long distanciaTotal;
    private Long tempoDeVoo;

    public Aresta(Aeroporto origem, Aeroporto destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public Aeroporto getOrigem() {
        return origem;
    }

    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
    }

    public Aeroporto getDestino() {
        return destino;
    }

    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }

    public Long getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(Long distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Long getTempoDeVoo() {
        return tempoDeVoo;
    }

    public void setTempoDeVoo(Long tempoDeVoo) {
        this.tempoDeVoo = tempoDeVoo;
    }
}
