/**
 * Essa é classe das GARAGEM DE VAGÕES do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 */

public class WagonGarage extends Garage<Wagon> {

    public WagonGarage(int length) {
        super(length); // Acessa diretamente o construtor da classe-mãe
    }

    /**
     * Método retorna o vagão indicado pelo ID, se não encontrado é nulo
     * @param id
     * @return Wagon ou null, se não achar
     */
    public Wagon get(int id){
        for(Wagon wagon : garage){
            if (wagon.getId() == id) return wagon;
        }
        return null;
    }
}