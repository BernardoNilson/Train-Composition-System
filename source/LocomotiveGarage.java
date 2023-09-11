/**
 * Essa é classe da GARAGEM DE LOCOMOTIVAS do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 */

 public class LocomotiveGarage extends Garage<Locomotive> {

    public LocomotiveGarage(int length) {
        super(length); // Acessa diretamente o construtor da classe-mãe
    }

    /**
     * Método retorna a locomotiva indicada pelo ID, se não encontrada é nulo
     * @param id
     * @return Locomotive ou null, se não achar
     */
    public Locomotive get(int id){
        for(Locomotive locomotive : garage){
            if (locomotive.getId() == id) return locomotive;
        }
        return null;
    }
}
