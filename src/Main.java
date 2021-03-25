import data_shape.Aeroporto;
import data_shape.Grafo;
import data_shape.Voo;
import service.ArquivoService;
import service.ErrosService;
import service.GrafoService;
import util.constants.ExemploConstants;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        try {
            ArquivoService.CONSERTA_TXT(ExemploConstants.PATH_TXT);
            grafo = ArquivoService.LER_ARQUIVO(ExemploConstants.PATH_TXT);
            mainMenu(grafo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void mainMenu(Grafo grafo) {
        int esc = -1;
        Scanner ler = new Scanner(System.in);
        while(esc != 0){
            System.out.println("");
            grafo.show(Boolean.TRUE);
            System.out.println("========== MENU ==========");
            System.out.println("1. Mostrar possibilidade de voos dado dois aeroportos;");
            System.out.println("2. dado um voo e mostrar a rota possível para o mesmo;");
            System.out.println("3. mostrar, a partir de um aeroporto definido, quais os voos diretos (sem escalas e/ou conexões) que partem dele e a lista desses destinos;");
            esc = ler.nextInt();
            switch (esc){
                case 1://5.A
                    mostrarPossibilidadesDeVoo(grafo, ler);
                    break;
                case 2://5.B
                    mostrarRota(grafo, ler);
                    break;
                case 3://5.C
                    voosSemConexaoOuEscala(grafo, ler);
                    break;
                case 4://5.D
                    break;
                case 5://5.E
                    break;
                case 6://5.F
                    break;
                default:
                    ErrosService.ERRO_OPCAO_INVALIDA();
                    break;
            }
        }

    }

    private static void voosSemConexaoOuEscala(Grafo grafo, Scanner ler) {
        grafo.mostraAeroportos();
        String origem = GrafoService.SELECIONA_AEROPORTO(grafo, ler);
        List<Voo> voos = GrafoService.VOOS_DIRETOS(grafo, origem);
        voos.forEach(voo -> System.out.println(voo.toString(Boolean.FALSE)));
        System.out.println("\nDestinos");
        System.out.println(voos.stream().map(Voo::getAeroportoDestino).collect(Collectors.joining(", ")));
    }

    private static void mostrarRota(Grafo grafo, Scanner ler) {
        grafo.mostraAeroportos();
        String origem = GrafoService.SELECIONA_AEROPORTO(grafo, ler);
        String destino = GrafoService.SELECIONA_AEROPORTO(grafo, ler);
        List<Aeroporto> caminho = GrafoService.procuraCaminho(grafo, grafo.getAeroportos().get(origem), grafo.getAeroportos().get(destino));
        if(Objects.isNull(caminho)){
            System.out.println("\n Não há rotas para tal aeroporto.");
        }else if (caminho.isEmpty()){
            System.out.println("\n O aeroporto origem e o destino são os mesmos.");
        }else{
            System.out.println("\n Caminho: " + caminho.stream().map(Aeroporto::getAbreviacao).collect(Collectors.joining(" -> ")));
        }
    }

    private static void mostrarPossibilidadesDeVoo(Grafo grafo, Scanner ler) {
        grafo.mostraAeroportos();
        String a1 = GrafoService.SELECIONA_AEROPORTO(grafo, ler);
        String a2 = GrafoService.SELECIONA_AEROPORTO(grafo, ler);
        grafo.getVoos().stream().filter(
            voo ->
                (Objects.equals(a1, voo.getAeroportoOrigem()) && Objects.equals(a2, voo.getAeroportoDestino()))
                || (Objects.equals(a2, voo.getAeroportoOrigem()) && Objects.equals(a1, voo.getAeroportoDestino()))
        ).forEach(
            voo -> System.out.println(voo.toString())
        );
    }
}
