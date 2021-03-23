import data_shape.Grafo;
import service.ArquivoService;
import util.constants.ExemploConstants;

public class Main {
    public static void main(String[] args) {
        try {
            ArquivoService.CONSERTA_TXT(ExemploConstants.PATH_TXT);
            ArquivoService.LER_ARQUIVO(ExemploConstants.PATH_TXT).show(Boolean.TRUE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
