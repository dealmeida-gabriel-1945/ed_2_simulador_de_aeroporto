package service;

import data_shape.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ArquivoService {
    public static Grafo LER_ARQUIVO(String caminho) throws IOException {
        Grafo grafo = new Grafo();
        File arquivo = new File(caminho);
        FileReader leitor = new FileReader(arquivo);   //reads the file
        BufferedReader leitorBuffado = new BufferedReader(leitor);  //creates a buffering character input stream
        StringBuffer stringBuffada = new StringBuffer();    //constructs a string buffer with no characters
        String linha;

        Integer contador = 0;

        while (Objects.nonNull(linha = leitorBuffado.readLine())){
            if(linha.length() > 0 && (linha.charAt(0) != '#')){
                if(linha.charAt(0) == '!'){
                    contador++;
                }else{
                    List<String> partes = Arrays.asList(linha.split(" "));
                    switch (contador){
                        case 0:
                            if(partes.get(2).equals("") || partes.get(3).equals("")){
                                int i = 0;
                            }
                            if(!partes.isEmpty()){
                                StringBuilder local = new StringBuilder();
                                for (int i = 4; i < partes.size(); i++) {
                                    local.append(partes.get(i)).append((i == (partes.size() - 1)) ? "" : " ");
                                }

                                Aeroporto aeroporto = new Aeroporto(
                                        partes.get(0),
                                        partes.get(1),
                                        Long.parseLong(partes.get(2)),
                                        Long.parseLong(partes.get(3)),
                                        local.toString()
                                );
                                grafo.getAeroportos().put(aeroporto.getAbreviacao(), aeroporto);
                            }
                            break;
                        case 1:
                            if(!partes.isEmpty()){
                                grafo.getAeroportos().get(partes.get(0)).getDestinos().put(partes.get(1), grafo.getAeroportos().get(partes.get(1)));
                            }
                            break;
                        case 2:
                            if(!partes.isEmpty()){
                                Aviao aviao = new Aviao(partes.get(0), Long.parseLong(partes.get(1)));
                                grafo.getAvioes().put(aviao.getModelo(), aviao);
                            }
                            break;
                        case 3:
                            if(!partes.isEmpty()){
                                Voo voo = new Voo();
                                voo.setLinhaAerea(partes.get(0));
                                voo.setVoo(partes.get(1));
                                voo.setAeroportoOrigem(partes.get(2));
                                voo.setHoraPartida(partes.get(3));
                                voo.setAeroportoDestino(partes.get(4));
                                voo.setHoraChegada(partes.get(5));
                                int index = 8;
                                try{
                                    voo.setComida(partes.get(6));
                                    voo.setParadas(Integer.parseInt(partes.get(7)));
                                }catch (Exception e){
                                    index = 7;
                                    voo.setComida("");
                                    voo.setParadas(Integer.parseInt(partes.get(6)));
                                }
                                voo.setTipo(partes.get(index));
                                index++;
                                for (int i = index; i < partes.size(); i++) {
                                    voo.getClasses().add(partes.get(i));
                                }
                                grafo.getVoos().add(voo);
                            }
                            break;
                    }
                }
            }
        }
        grafo.getVoos().forEach(voo -> voo.calculaPesosRestantes(grafo.getAeroportos()));
        return grafo;
    }

    public static void CONSERTA_TXT(String caminho) throws IOException{
        File arquivo = new File(caminho);
        FileReader leitor = new FileReader(arquivo);   //reads the file
        BufferedReader leitorBuffado = new BufferedReader(leitor);  //creates a buffering character input stream
        StringBuffer stringBuffada = new StringBuffer();    //constructs a string buffer with no characters
        String linha;

        String output = "";

        Integer contador = 0;
        while (Objects.nonNull(linha = leitorBuffado.readLine())){
            String toAdd = linha;
            if(toAdd.length() > 0 && (toAdd.charAt(0) != '#')){
                if(toAdd.charAt(0) == '!'){
                    contador++;
                }else {
                    if(contador == 3 && toAdd.charAt(2) != ' '){
                        toAdd = toAdd.substring(0, 2) + ' ' + toAdd.substring(3);
                    }
                    while (toAdd.contains("  ")){
                        toAdd = toAdd.substring(0, toAdd.indexOf("  ")) + ' ' + toAdd.substring(toAdd.indexOf("  ") + 2);
                    }
                }
            }
            output += toAdd + '\n';
        }
        FileOutputStream fos = new FileOutputStream(caminho);
        fos.write(output.getBytes(StandardCharsets.UTF_8));
    }
}
