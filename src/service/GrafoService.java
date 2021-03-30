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

    public static Boolean possuiAeroportosInacessiveis(Grafo grafo) {
        List<Boolean> allVisitados = new ArrayList<>();
        grafo.getAeroportos()
            .forEach((s, aeroporto) -> {
                allVisitados.add(chegaATodos(aeroporto, grafo.getAeroportos()));
            });
        return allVisitados.stream().anyMatch(i -> !i);
    }

    private static Boolean chegaATodos(Aeroporto aeroporto, HashMap<String, Aeroporto> aeroportos){
        List<String> visitados = new ArrayList<>();
        aeroporto.getDestinos()
            .forEach((s, destino) -> visita(visitados, destino));
        if(aeroporto.getAbreviacao().equals("JOJ")){
            int ia = 0;
        }
        return aeroportos.keySet().containsAll(visitados) && !visitados.isEmpty();
    }

    private static void visita(List<String> visitados, Aeroporto atual){
        visitados.add(atual.getAbreviacao());
        atual.getDestinos()
            .forEach((s, destino) -> {
                if(!visitados.contains(s)){
                    visita(visitados, destino);
                }
            });
    }

    public static List<Aeroporto> procuraMenorCaminho(Grafo grafo, Aeroporto origem, Aeroporto destino) {
        HashMap<String, Boolean> visitados = new HashMap<>();
        grafo.getAeroportos().forEach((s, aeroporto) -> visitados.put(s, Boolean.FALSE));//seta todos como não visitados
        return menorCaminho(origem, destino, new ArrayList<>(), visitados);
    }

    private static List<Aeroporto> menorCaminho(Aeroporto atual, Aeroporto destino, List<Aeroporto> menorCaminho, HashMap<String, Boolean> visitados){

        menorCaminho.add(atual);
        HashMap<String, Boolean> visitadosCpy = new HashMap<>(visitados);
        if(Objects.equals(atual.getAbreviacao(), destino.getAbreviacao())){
            return menorCaminho;
        }
        if(visitadosCpy.get(atual.getAbreviacao())){
            return menorCaminho;
        }else {
            visitadosCpy.put(atual.getAbreviacao(), Boolean.TRUE);
        }

        List<List<Aeroporto>> caminhos = new ArrayList<>();
        atual.getDestinos().forEach(
                (s, aeroporto) -> {
                    visitadosCpy.clear();
                    visitadosCpy.putAll(visitados);
                    Optional.ofNullable(menorCaminho(aeroporto, destino, new ArrayList<>(), visitadosCpy)).ifPresent(l -> {
                        if (!l.isEmpty() && Objects.equals(l.get(l.size() - 1).getAbreviacao(), destino.getAbreviacao())) {
                            caminhos.add(l);
                        }
                    });
                }
        );
        List<Double> totais = new ArrayList<>();
        for (List<Aeroporto> aeroportos : caminhos) {
            Double distancia = 0d;
            for (int i = 1; i < aeroportos.size(); i++) {
                distancia += AeroportoService.calculaDistanciaEntreAeroportos(aeroportos.get(i - 1), aeroportos.get(i));
            }
            totais.add(distancia);
        }
        if(caminhos.isEmpty()){
            return null;
        }else{
            List<Aeroporto> aux = new ArrayList<>();
            aux.add(atual);
            aux.addAll(caminhos.get(totais.indexOf(totais.stream().mapToDouble(i -> i).min().getAsDouble())));
            return aux;
        }
    }

    public static List<Aeroporto> caminhoPorTodosAeroportos(Grafo grafo, String origem) {
        HashMap<String, Boolean> visitados = new HashMap<>();
        grafo.getAeroportos().forEach((s, aeroporto) -> visitados.put(s, Boolean.FALSE));//seta todos como não visitados
        return menorCaminhoPorTodosAeroportos(Boolean.TRUE, grafo.getAeroportos().get(origem), grafo.getAeroportos().get(origem), new ArrayList<>(), visitados);
    }

    private static List<Aeroporto> menorCaminhoPorTodosAeroportos(Boolean primeiraRodada, Aeroporto origem, Aeroporto atual, ArrayList<Aeroporto> menorCaminho, HashMap<String, Boolean> visitados) {
        menorCaminho.add(atual);
        HashMap<String, Boolean> visitadosCpy = new HashMap<>(visitados);
        if(!primeiraRodada && Objects.equals(atual.getAbreviacao(), origem.getAbreviacao())){
            return menorCaminho;
        }
        if(visitadosCpy.get(atual.getAbreviacao())){
            return menorCaminho;
        }else {
            visitadosCpy.put(atual.getAbreviacao(), Boolean.TRUE);
        }

        List<List<Aeroporto>> caminhos = new ArrayList<>();
        atual.getDestinos().forEach(
                (s, aeroporto) -> {
                    visitadosCpy.clear();
                    visitadosCpy.putAll(visitados);
                    Optional.ofNullable(menorCaminhoPorTodosAeroportos(Boolean.FALSE, origem, aeroporto, new ArrayList<>(), visitadosCpy)).ifPresent(l -> {
                        if (!l.isEmpty() && Objects.equals(l.get(l.size() - 1).getAbreviacao(), origem.getAbreviacao())) {
                            caminhos.add(l);
                        }
                    });
                }
        );
        List<Double> totais = new ArrayList<>();
        for (List<Aeroporto> aeroportos : caminhos) {
            if(Objects.equals(origem.getAbreviacao(), aeroportos.get(aeroportos.size() - 1).getAbreviacao())){
                Double distancia = 0d;
                for (int i = 1; i < aeroportos.size(); i++) {
                    distancia += AeroportoService.calculaDistanciaEntreAeroportos(aeroportos.get(i - 1), aeroportos.get(i));
                }
                totais.add(distancia);
            }
        }
        if(caminhos.isEmpty()){
            return null;
        }else{
            List<Aeroporto> aux = new ArrayList<>();
            aux.add(atual);
            aux.addAll(caminhos.get(totais.indexOf(totais.stream().mapToDouble(i -> i).min().getAsDouble())));
            return aux;
        }
    }
}
