/**
 * Essa é classe do PÁTIO FERROVIÁRIO DE TRENS do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 */

 public class TrainGarage extends Garage<Train> {

    public TrainGarage(int length) {
        super(length); // Acessa diretamente o construtor da classe-mãe
    }

    /**
     * Método retorna o trem indicado pelo ID, se não encontrado é nulo
     * @param id
     * @return Train ou null, se não achar
     */
    public Train get(int id){
        for(Train train : garage){
            if (train.getId() == id) return train;
        }
        return null;
    }

    /**
     * Método verifica se o ID informado é válido
     * @param id
     * @return true, se não estiver sendo utilizado
     */
    public boolean isValid(int id){
        for(Train train : garage){
            if (train.getId() == id) return false;
        }
        return true;
    }

    /**
     * Método cria um novo trem, se houver posições livres na garagem e se o ID for válido
     * @param id - Novo ID pro trem
     * @param locomotive - Locomotiva que vai compor o trem
     * @return true, caso a criação tenha sido um sucesso
     */
    public boolean createTrain(int id, Locomotive locomotive){
        if (getCount() <= getLength() && isValid(id) && locomotive != null){
            add(new Train(id, locomotive));
            return true;
        } else return false;
    }

}