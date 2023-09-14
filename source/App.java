import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Essa é a aplicação principal do Trabalho de Programação Orientada a Objetos. Para mais informações, consulte o README.md
 * 
 * @author Bernardo Nilson - PUCRS
 * @version 12.09.2023
 */
public class App {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // Geração das locomotivas e dos vagões
        WagonGarage wagonGarage = new WagonGarage(15);
        LocomotiveGarage locomotiveGarage = new LocomotiveGarage(15);
        TrainGarage trainGarage = new TrainGarage(20);

        for (int i = 0; i < 5; i++){
            Wagon wagon = new Wagon(i, (i*5 + 4), null);
            wagonGarage.add(wagon);

            Locomotive locomotive = new Locomotive(i, (i*4 + 9), (i*2 + 2), null);
            locomotiveGarage.add(locomotive);
        }

        // Declarações
        String choice, select;
        int idTrain, idWagon, idLocomotive;
        Locomotive selectedLocomotive;
        Train selectedTrain;
        Wagon selectedWagon;

        // Menu
        do {
            choice = showMenu();

            switch (choice) {
                case "A":
                    showMessage("CRIAR UM TREM");

                    System.out.print("Informe um novo ID: ");
                    idTrain = scan.nextInt();
                    idLocomotive = showAvailableOptins("LOCOMOTIVAS", locomotiveGarage.toString());
                    selectedLocomotive = locomotiveGarage.get(idLocomotive);
                    
                    if (trainGarage.createTrain(idTrain, selectedLocomotive)){
                        resultMessage(true);
                        locomotiveGarage.remove(selectedLocomotive);
                    } else resultMessage(false);
                    break;

                case "B":
                    showMessage("EDITAR UM TREM");
                    idTrain = showAvailableOptins("TRENS", trainGarage.toString());
                    selectedTrain = trainGarage.get(idTrain);

                    if(!trainGarage.isValid(idTrain)){
                        select = showSubMenu();

                        switch (select) {
                            case "A":
                                showMessage("INSERIR UMA LOCOMOTIVA");
                                idLocomotive = showAvailableOptins("LOCOMOTIVAS", locomotiveGarage.toString());
                                selectedLocomotive = locomotiveGarage.get(idLocomotive);

                                if(selectedTrain.addLocomotive(selectedLocomotive)){
                                    resultMessage(true);
                                    locomotiveGarage.remove(selectedLocomotive);
                                } else resultMessage(false);
                                break;

                            case "B":
                                showMessage("INSERIR UM VAGÃO");
                                idWagon = showAvailableOptins("VAGÕES", wagonGarage.toString());
                                selectedWagon = wagonGarage.get(idWagon);

                                if(selectedTrain.addWagon(selectedWagon)){
                                    resultMessage(true);
                                    wagonGarage.remove(selectedWagon);
                                } else resultMessage(false);
                                break;

                            case "C":
                                showMessage("REMOVER O ÚLTIMO ELEMENTO");
                                
                                if(selectedTrain.getWagonCount() > 0){
                                    resultMessage(true);
                                    wagonGarage.add(selectedTrain.removeLastWagon());
                                } else if (selectedTrain.getLocomotiveCount() > 1){
                                    resultMessage(true);
                                    locomotiveGarage.addLocomotive(selectedTrain.removeLastLocomotive());
                                } else resultMessage(false);
                                break;

                            case "D":
                                showMessage("LISTAR TODAS AS LOCOMOTIVAS LIVRES");
                                System.out.println(locomotiveGarage.toString());
                                break;
                            
                            case "E":
                                showMessage("LISTAR OS VAGÕES LIVRES");
                                System.out.println(wagonGarage.toString());
                                break;

                            case "Z":
                                System.out.println("Você está saindo da edição");
                                break;

                            default:
                                resultMessage(false);
                                break;
                        }
                    } else System.out.println("O ID informado não foi encontrado, tente novamente!");
                    break;

                case "C":
                    showMessage("EXIBIR LISTA DE TRENS JÁ CRIADOS");
                    System.out.println(trainGarage.toString());
                    break;

                case "D":
                    showMessage("DESFAZER UM TREM");
                    idTrain = showAvailableOptins("TRENS", trainGarage.toString());
                    selectedTrain = trainGarage.get(idTrain);

                    if (selectedTrain != null){
                        // Libera os vagões e aloca cada um na garagem
                        while (selectedTrain.getWagonCount() > 0){
                            wagonGarage.addWagon(selectedTrain.removeLastWagon());
                        }

                        // Libera as locomotivas e aloca cada uma na garagem
                        while (selectedTrain.getLocomotiveCount() > 0){
                            if (selectedTrain.getLocomotiveCount() == 1) locomotiveGarage.addLocomotive(selectedTrain.removeFirstLocomotive());
                            else locomotiveGarage.addLocomotive(selectedTrain.removeLastLocomotive());
                        }
                        
                        resultMessage(trainGarage.remove(selectedTrain));
                    } else resultMessage(false);
                    break;

                case "E":
                    showMessage("SALVAR INFORMAÇÕES (.txt)");
                    // Tentamos executar o salvamento do arquivo
                    try {
                        File file = new File(new File(".."), "saveFile.txt");   // Cria últimao arquivo
                        FileWriter writer = new FileWriter(file);   // Abre o escritor dentro do arquivo
                        // Escreve cada locomotiva disponível
                        for (Locomotive locomotive : locomotiveGarage.getGarage()) {
                            writer.write(locomotive.getId() + ",");
                            writer.write(locomotive.getWeightCapacity() + ",");
                            writer.write(locomotive.getWagonCapacity() + ",");
                            if (locomotive.getTrain() != null) writer.write(locomotive.getTrain().getId());
                            else writer.write("-1");
                            writer.write("\n");
                        }
                        // Escreve cada locomotiva em uso
                        for (Train train : trainGarage.getGarage()) {
                            for (Locomotive locomotive : train.getLocomotives()) {
                                writer.write(locomotive.getId() + ",");
                                writer.write(locomotive.getWeightCapacity() + ",");
                                writer.write(locomotive.getWagonCapacity() + ",");
                                if (locomotive.getTrain() != null) writer.write(String.valueOf(locomotive.getTrain().getId()));
                                else writer.write("-1");
                                writer.write("\n");
                            }
                        }
                        writer.close();
                        resultMessage(true);
                    } catch (Exception e){
                        resultMessage(false);
                        System.out.println(e);
                    }
                    break;

                case "F":
                    showMessage("CARREGAR INFORMAÇÕES (.txt)");
                    try {
                        
                        File archive = new File(new File(".."), "loadFile.txt");    // Busca o arquivo
                        Scanner in = new Scanner(archive);    // Abre o Scanner dentro do arquivo
                        // Para cada linha no arquivo
                        int i = 0;
                        while (in.hasNextLine()) {

                            String line = in.nextLine();
                            String[] parts = line.split(",");

                            int id = Integer.parseInt(parts[0]);
                            double weightCapacity = Double.parseDouble(parts[1]);
                            int wagonCapacity = Integer.parseInt(parts[2]);
                            int trainId = Integer.parseInt(parts[3]);
                            Train train = trainGarage.get(trainId);
                            Locomotive locomotive = new Locomotive(id, weightCapacity, wagonCapacity, train);

                            if (train == null) locomotiveGarage.addLocomotive(locomotive);
                            else train.addLocomotive(locomotive);
                        }
                        in.close();
                        resultMessage(true);
                    } catch (Exception e){
                        resultMessage(false);
                        System.out.println(e);
                    }
                    break;

                case "Z":
                    System.out.println("Você está saindo do programa. Até mais!");
                    break;

                default:
                    System.out.println("Você pode ter se confundido. Tente novamente.");
                    break;
            }
        } while (!choice.equals("Z"));
    }

    /**
     * Método exibe um menu ao usuário e retorna a escolha
     * @return String - escolha do usuário, independente se está certa ou não
     */
    public static String showMenu(){
        System.out.println("----------------------------------------------------");
        System.out.println(" > Por favor, selecione uma das opções:");
        System.out.println(" ( A ) - CRIAR");
        System.out.println(" ( B ) - EDITAR");
        System.out.println(" ( C ) - EXIBIR LISTA");
        System.out.println(" ( D ) - DESFAZER");
        System.out.println(" ( E ) - SALVAR INFORMAÇÕES (.txt)");
        System.out.println(" ( F ) - CARREGAR INFORMAÇÕES (.txt)");
        System.out.println(" ( Z ) - SAIR");
        System.out.println("----------------------------------------------------");
        System.out.print("Sua resposta: ");
        return scan.next().trim().toUpperCase();
    }

    /**
     * Método exibe um menu ao usuário
     * @param message - mensagem a ser enviada
     */
    private static void showMessage(String message){
        System.out.println(" Você selecionou: " + message + "!");
    }

    /**
     * Método exibe um menu ao usuário e retorna a escolha
     * @return String - escolha do usuário, independente se está certa ou não
     */
    public static String showSubMenu(){
        System.out.println("----------------------------------------------------");
        System.out.println(" > Por favor, selecione uma das opções de edição:");
        System.out.println(" ( A ) - INSERIR LOCOMOTIVA");
        System.out.println(" ( B ) - INSERIR VAGÃO");
        System.out.println(" ( C ) - REMOVER ÚLTIMO ELEMENTO");
        System.out.println(" ( D ) - EXIBIR LOCOMOTIVAS LIVRES");
        System.out.println(" ( E ) - EXIBIR VAGÕES LIVRES");
        System.out.println(" ( Z ) - ENCERRAR EDIÇÃO");
        System.out.println("----------------------------------------------------");
        System.out.print("Sua resposta: ");
        return scan.next().trim().toUpperCase();
    }

    /**
     * Método exibe as opções disponíveis ao usuário e retorna a escolha
     * @return int - escolha do usuário, independente se está certa ou não
     */
    public static int showAvailableOptins(String type, String options){
        System.out.println(" - Opções de " + type + " disponíveis:");
        System.out.println(options);
        System.out.print("Informe o ID: ");
        return scan.nextInt();
    }

    /**
     * Método exibe uma mensagem do resultado da operação
     * @param boolean - true para sucesso e false para fracasso
     */
    public static void resultMessage(boolean mode){
        if (mode) System.out.println(" - Operação concluída com sucesso!");
        else System.out.println(" - Algo deu errado, revise as informações e tente de novo! Operação não autorizada");
    }
}
