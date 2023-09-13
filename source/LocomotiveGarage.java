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
    /**
     * Método verifica se o ID informado é válido
     * @param id
     * @return true, se não estiver sendo utilizado
     */
    public boolean isValid(int id){
        for(Locomotive locomotive : garage){
            if (locomotive.getId() == id) return false;
        }
        return true;
    }

    /**
     * Método cria uma nova locomotiva, se houver posições livres na garagem e se o ID for válido
     * @param id - Novo ID pro trem
     * @return true, caso a criação tenha sido um sucesso
     */
    public boolean addLocomotive(int id){
        if (getCount() <= getLength() && isValid(id)) return add(get(id));
        else return false;
    }

    /**
     * Método cria uma nova locomotiva, se houver posições livres na garagem e se o ID for válido
     * @param id - Novo ID pro trem
     * @return true, caso a criação tenha sido um sucesso
     */
    public boolean addLocomotive(Locomotive locomotive){
        if (isValid(locomotive.getId())) return add(locomotive);
        else return false;
    }
}
