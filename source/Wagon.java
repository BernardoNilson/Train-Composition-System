/**
 * Essa é classe dos VAGÕES do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 */

public class Wagon {
    // Atributos de Wagon
    private int id;
    private double weightCapacity;
    private Train train;

    /**
     * Método construtor de Wagon
     * @param id - Novo ID pra locomotiva
     * @param weightCapacity - Qual a capacidade de carga, em toneladas
     * @param train - Trem ao qual está associado, null se está na garagem
     */
    public Wagon (int id, double weightCapacity, Train train){
        this.id = id;
        this.weightCapacity = weightCapacity;
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
     * Método de exibição de Wagon
     * @return String 
     */
    public String toString(){
        return "- ID: " + id +
            " | Capacidade (TON): " + weightCapacity +
            " | Trem: " + ((train == null) ? "Nenhum" : train.getId());
    }
}
