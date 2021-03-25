package service;

import data_shape.Aeroporto;
import data_shape.Grafo;
import data_shape.Voo;

import java.util.*;
import java.util.stream.Collectors;

public class GrafoService {

    public static List<Voo> VOOS_DIRETOS(Grafo grafo, String origem){
        return grafo.getVoos().stream().filter(
            voo -> Objects.equals(voo.getAeroportoOrigem(), origem)
                && (voo.getParadas() == 0)
        ).collect(Collectors.toList());
    }
    public static List<Aeroporto> procuraCaminho(Grafo grafo, Aeroporto origem, Aeroporto destino){
        if(Objects.equals(origem.getAbreviacao(), destino.getAbreviacao())){
            return Collections.emptyList();
        }

        HashMap<String, Boolean> visitados = new HashMap<>();
        grafo.getAeroportos().forEach((s, aeroporto) -> visitados.put(s, Boolean.FALSE));//seta todos como não visitados
        List<Aeroporto> caminho = new ArrayList<>();

        caminho = procuraCaminho(visitados, origem, destino, caminho);

        if (Objects.nonNull(caminho) && !caminho.isEmpty() && Objects.equals(caminho.get(caminho.size() - 1).getAbreviacao(), destino.getAbreviacao())){
            return caminho;
        }
        return null;
    }

    private static List<Aeroporto> procuraCaminho(
        HashMap<String, Boolean> visitados, Aeroporto atual,
        Aeroporto destino, List<Aeroporto> caminho
    ){
        visitados.put(atual.getAbreviacao(), Boolean.TRUE);
        List<Aeroporto> caminhoCpy = new ArrayList<>(caminho);
        caminhoCpy.add(atual);
        if (Objects.equals(atual.getAbreviacao(), destino.getAbreviacao())){
            return caminhoCpy;
        }

        for (Map.Entry<String, Aeroporto> entry: atual.getDestinos().entrySet()){
            if (!visitados.get(entry.getKey())){
                List<Aeroporto> aux = procuraCaminho(visitados, entry.getValue(), destino, caminhoCpy);
                if (Objects.nonNull(aux)) return aux;
            }
        }
        return null;
    }

    public static String SELECIONA_AEROPORTO(Grafo grafo, Scanner ler){
        Aeroporto ae = null;
        while (Objects.isNull(ae)){
            System.out.print("Digite o aeroporto desejado: ");
            ae = grafo.getAeroportos().get(ler.next());
            if(Objects.isNull(ae)) ErrosService.ERRO_DADO_NAO_ENCONTRADO();
        }
        return ae.getAbreviacao();
    }
}
