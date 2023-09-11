import java.util.ArrayList;
import java.util.List;

/**
 * Essa é classe-mãe da GARAGENS do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 */

public class Garage<Type> {
    // Atributos de Garage
    protected ArrayList<Type> garage;
    private int length;

    /**
     * Método construtor de Garage
     * @param length - Quantos vagões a garagem suporta
     * @throws IllegalArgumentException se length < 0
     */
    public Garage(int length) {
        if (length < 0) throw new IllegalArgumentException();
        this.garage = new ArrayList<>(length);
        this.length = length;
    }

    /**
     * @return garage
     */
    public List<Type> getGarage() {
        return garage;
    }

    /**
     * @return length - tamanho da garagem
     */
    public int getLength() {
        return length;
    }

    /**
     * @return count
     */
    public int getCount() {
        return garage.size();
    }

    /**
     * Método adiciona o item na garagem
     * @param item - Wagon, Locomotive ou Train
     * @return true, caso tenha adicionado com sucesso
     */
    public boolean add(Type item){
        if(getCount() <= getLength()){
            garage.add(item);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método exclui o item informado
     * @return true, caso a exclusão tenha sido um sucesso
     */
    public boolean remove(Type item) {
        if(!garage.isEmpty()){
            garage.remove(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método de exibição e listagem dos itens da Garage
     * @return String 
     */
    @Override
    public String toString() {
        String result = "----------------------------------------------------\n";

        for(Type item : garage){
                result += item.toString() + "\n";
        }

        result += "----------------------------------------------------";
        return result;
    }
}
