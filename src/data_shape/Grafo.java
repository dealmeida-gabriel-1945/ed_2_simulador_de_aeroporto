package data_shape;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Grafo {
    private HashMap<String, Aeroporto> aeroportos = new HashMap<>();
    private HashMap<String, Aviao> avioes = new HashMap<>();
    private List<Voo> voos = new ArrayList<>();

    public void show(Boolean minimo){
        System.out.println(this.montaStringShow(minimo));
    }

    private String montaStringShow(Boolean minimo) {
        StringBuilder sb = new StringBuilder();
        if(!minimo){
            sb.append("############################################################### AEROPORTOS \n");
            aeroportos.forEach((s, aeroporto) -> sb.append(aeroporto.toString()));
            sb.append("############################################################### AVIOES \n");
            avioes.forEach((s, aviao) -> sb.append(aviao.toString()));
            sb.append("############################################################### VOOS \n");
            voos.forEach((voo) -> sb.append(voo.toString()));
        }else{
            sb.append("Quantidade de aeroportos: ");
            sb.append(aeroportos.size());
            sb.append(" Quantidade de aviões: ");
            sb.append(avioes.size());
            sb.append(" Quantidade de voos: ");
            sb.append(voos.size());
        }

        return sb.toString();
    }


    public HashMap<String, Aviao> getAvioes() {
        return avioes;
    }

    public void setAvioes(HashMap<String, Aviao> vaioes) {
        this.avioes = vaioes;
    }

    public HashMap<String, Aeroporto> getAeroportos() {
        return aeroportos;
    }

    public void setAeroportos(HashMap<String, Aeroporto> aeroportos) {
        this.aeroportos = aeroportos;
    }

    public List<Voo> getVoos() {
        return voos;
    }

    public void setVoos(List<Voo> voos) {
        this.voos = voos;
    }


}
