package service;

import data_shape.Aeroporto;

import java.util.Objects;

public class AeroportoService {
    public static Double calculaDistanciaEntreAeroportos(Aeroporto aeroporto1, Aeroporto aeroporto2){
        if(Objects.isNull(aeroporto1) || Objects.isNull(aeroporto2)) return 0d;

        return Math.sqrt(
            Math.pow(aeroporto1.getCoordenadaX() - aeroporto2.getCoordenadaX(), 2) +
            Math.pow(aeroporto1.getCoordenadaY() - aeroporto2.getCoordenadaY(), 2)
        );
    }
}
