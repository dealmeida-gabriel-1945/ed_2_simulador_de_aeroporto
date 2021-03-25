package service;

public class ErrosService {
    private static void MOSTRA_ERRO(String erro){
        System.out.println("////////////////////////////////////////////");
        System.out.println("ERRO: " + erro);
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }

    public static void ERRO_OPCAO_INVALIDA(){
        MOSTRA_ERRO("Opção inválida!");
    }

    public static void ERRO_DADO_NAO_ENCONTRADO(){
        MOSTRA_ERRO("Dado(s) não encontrado(s)!");
    }
}
