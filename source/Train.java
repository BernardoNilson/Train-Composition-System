import java.util.ArrayList;
import java.util.List;

/**
 * Essa é classe de COMPOSIÇÃO / TRENS do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 */

public class Train {
    // Atributos de Train
    private int id;
    private ArrayList<Locomotive> locomotives;
    private ArrayList<Wagon> wagons;
    private int wagonsCapacity;
    private double weightCapacity, currentWeight;

    /**
     * Método construtor de Train
     * @param id - Novo ID pra locomotiva
     * @param locomotive - primeria locomotiva do trem
     */
    public Train(int id, Locomotive locomotive){
        this.id = id;
        
        locomotives = new ArrayList<>();
        wagons = new ArrayList<>();

        locomotives.add(locomotive);
        locomotive.setTrain(this);
        
        wagonsCapacity = locomotive.getWagonCapacity();
        weightCapacity = locomotive.getWeightCapacity();
        currentWeight = 0;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return locomotives
     */
    public List<Locomotive> getLocomotives() {
        return locomotives;
    }
    
    /**
     * @return wagons
     */
    public List<Wagon> getWagons() {
        return wagons;
    }
    
    /**
     * @return wagonsCapacity
     */
    public int getWagonsCapacity() {
        return wagonsCapacity;
    }
    
    /**
     * @return weightCapacity
     */
    public double getWeightCapacity() {
        return weightCapacity;
    }

    /**
     * @return currentWeight
     */
    public double getCurrentWeight() {
        return currentWeight;
    }
    
    /**
     * Método retorna a quantidade de locomotivas no trem
     * @return quantidade de locomotivas
     */
    public int getLocomotiveCount(){
        return locomotives.size();
    }

    /**
     * Método retorna a quantidade de vagões no trem
     * @return quantidade de locomotivas
     */
    public int getWagonCount(){
        return wagons.size();
    }

    /**
     * Método adiciona uma locomotiva no trem, se não houver nenhum vagão 
     * @param newLocomotive - nova locomotiva
     * @return true, se adicionado com sucesso
     */
    public boolean addLocomotive(Locomotive newLocomotive){
        if (wagons.isEmpty()){
            locomotives.add(newLocomotive);
            newLocomotive.setTrain(this);
            updateCapacityValues();
            return true;
        } else return false;
    }
    
    /**
     * Método adiciona um novo vagão no trem, se estiver na capacidade
     * @param newWagon - novo vagão
     * @return true, se adicionado com sucesso
     */
    public boolean addWagon(Wagon newWagon){
        double newWeight = getCurrentWeight() + newWagon.getWeightCapacity();
        if (newWeight <= getWeightCapacity()){
            wagons.add(newWagon);
            newWagon.setTrain(this);
            updateCapacityValues();
            return true;
        } else return false;
    }

    /**
     * Método remove a última locomotiva do trem, se ainda houve vagões para remover
     * @return Locomotive removida
     */
    public Locomotive removeLastLocomotive(){
        if (wagons.isEmpty() && getLocomotiveCount() > 1){
            Locomotive temp = locomotives.get(getWagonCount() - 1);
            locomotives.remove(getWagonCount() - 1);
            temp.setTrain(null);
            updateCapacityValues();
            return temp;
        } else return null;
    }

    /**
     * Método remove o último vagão do trem, se ainda houve vagões para remover
     * @return Wagon removido
     */
    public Wagon removeLastWagon(){
        if (!wagons.isEmpty()){
            Wagon temp = wagons.get(getWagonCount() - 1);
            wagons.remove(getWagonCount() - 1);
            temp.setTrain(null);
            return temp;
        } else return null;
    }
    
    /**
     * Método atualiza as capacidade de peso, de tração de vagões e de peso atual.
     */
    public void updateCapacityValues(){
        double newCurrent = 0;
        double newWeight = 0;
        int newWagons = 0;

        for(Wagon wagon : wagons){
            newCurrent += wagon.getWeightCapacity();
        }
        for(Locomotive locomotive : locomotives){
            newWagons += locomotive.getWagonCapacity();
            newWeight += locomotive.getWeightCapacity();
        }
        if (getLocomotiveCount() > 1) newWagons = (int)(newWagons*0.9);

        this.currentWeight = newCurrent;
        this.weightCapacity = newWeight;
        this.wagonsCapacity = newWagons;
    }

    /**
     * Método de exibição de TRAIN 
     * @return String 
     */
    public String toString(){
        return "- ID: " + id +
            " | Capacidade (TON): " + weightCapacity +
            " | Carga atual (TON): " + currentWeight +
            " | Vagões (MAX): " + wagonsCapacity +
            " | Qtd. Locomotivas: " + getLocomotiveCount() +
            " | Qtd. Vagões: " + getWagonCount();
    }
}