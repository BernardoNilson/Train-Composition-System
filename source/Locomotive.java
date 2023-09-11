/**
 * Essa é classe das LOCOMOTIVAS do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 */

public class Locomotive {
    // Atributos de Locomotive
    private int id;
    private double weightCapacity;
    private int wagonCapacity;
    private Train train;

    /**
     * Método construtor de Locomotive
     * @param id - Novo ID pra locomotiva
     * @param weightCapacity - Qual a capacidade de carga, em toneladas
     * @param wagonCapacity - Quantos vagões a locomotiva consegue puxar
     * @param train - Trem ao qual está associado, null se está na garagem
     */
    public Locomotive (int id, double weightCapacity, int wagonCapacity, Train train){
        this.id = id;
        this.weightCapacity = weightCapacity;
        this.wagonCapacity = wagonCapacity;
        this.train = train;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * @return weightCapacity
     */
    public double getWeightCapacity() {
        return weightCapacity;
    }
    /**
     * @return wagonCapacity
     */
    public int getWagonCapacity() {
        return wagonCapacity;
    }
    /**
     * @return Train
     */
    public Train getTrain() {
        return train;
    }

    /**
     * Modifica o atributo trem
     * @param train
     */
    public void setTrain(Train train) {
        this.train = train;
    }

    /**
     * Método de exibição de LOCOMOTIVE 
     * @return String 
     */
    public String toString(){
        return "- ID: " + id +
            " | Capacidade (TON): " + weightCapacity +
            " | Vagões: " + wagonCapacity +
            " | Trem: " + ((train == null) ? "Nenhum" : train.getId());
    }
}
