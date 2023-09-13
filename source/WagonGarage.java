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

    /**
     * Método verifica se o ID informado é válido
     * @param id
     * @return true, se não estiver sendo utilizado
     */
    public boolean isValid(int id){
        for(Wagon wagon : garage){
            if (wagon.getId() == id) return false;
        }
        return true;
    }

    /**
     * Método adiciona um novo vagão, se houver posições livres na garagem e se o ID for válido
     * @param wagon
     * @return true, caso a criação tenha sido um sucesso
     */
    public boolean addWagon(Wagon wagon){
        if (getCount() <= getLength() && isValid(wagon.getId())) return add(wagon);
        else return false;
    }
}