import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\erickbarbosa-ieg\\OneDrive - Instituto J&F\\Área de Trabalho\\Tech\\LPR\\Codes\\manipulacao-arquivos\\src\\inteiros10.txt");
        Scanner inputFile = new Scanner(file);
        int media = 0;
        double cont = 0;
        int cont2 = 1;

        while (inputFile.hasNextLine()){
            System.out.println("\n\n-- Linha "+cont2+" --");
            String[] xi = inputFile.nextLine().split(" ");
            for( String x: xi){
                System.out.print(x+"\t");
                media += Integer.parseInt(x);
                cont++;
            }
            cont2++;
        }
        inputFile.close();
        System.out.println("\n\nMédia ➜ "+media/cont);
    }
}