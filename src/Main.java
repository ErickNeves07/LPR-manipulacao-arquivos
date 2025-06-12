import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner inputFile = null;
//        Scanner input = new Scanner(System.in);
//        boolean verificador = false;
//        do {
//            try {
//                File file = new File("C:\\Users\\erickbarbosa-ieg\\OneDrive - Instituto J&F\\Área de Trabalho\\Tech\\LPR\\Codes\\manipulacao-arquivos\\src\\inteiros10.txt");
//                inputFile = new Scanner(file);
//                verificador = true;
//            } catch (FileNotFoundException fne){
//                System.out.println("Arquivo não encontrado!");
//                input.nextLine();
//            }
//        } while (!verificador);
//        int media = 0;
//        double cont = 0;
//        int cont2 = 1;
//
//        while (inputFile.hasNextLine()){
//            System.out.println("\n\n-- Linha "+cont2+" --");
//            String[] xi = inputFile.nextLine().split(" ");
//            for( String x: xi){
//                try{
//                media += Integer.parseInt(x);
//                System.out.print(x+"\t");
//                cont++;
//                } catch (NumberFormatException nfe){
//                    System.out.print("("+x + ") - Não é um número inteiro aceito");
//                }
//            }
//            cont2++;
//        }
//        inputFile.close();
//        try {
//            if (cont == 0) throw new IllegalArgumentException("Sem valores inteiros no arquivo!");
//            System.out.println("\n\nMédia ➜ "+media/cont);
//        } catch (IllegalArgumentException iae){
//            System.out.println("\n\n"+iae);
//        }

        Scanner input = new Scanner(System.in);
        Scanner inputFile = null;
        boolean verificador = false;
        int i =0;

            try {
                File file = new File("src/produtos.txt");
                inputFile = new Scanner(file);
            } catch (FileNotFoundException fne){
                System.out.println("Arquivo não encontrado!");
            }

        while (inputFile.hasNextLine()){
            inputFile.nextLine();
            i++;
        }

        inputFile.close();
        try {
            inputFile = new Scanner(new File("src/produtos.txt"));
        } catch (FileNotFoundException fne){
            System.out.println("Erro ao reabrir o arquivo!");
        }

        String[] nome = new String[i];
        Double[] preco = new Double[i];

        for (int contador = 0; contador<nome.length; contador++){
            nome[contador] = inputFile.next();
            while (!inputFile.hasNextDouble()){
                nome[contador] = nome[contador]+" ".concat(inputFile.next());
            }
            preco[contador] = inputFile.nextDouble();
        }
        inputFile.close();

        do {
            try {
                System.out.print("Digite a posição desejada (0 a " + (nome.length - 1) + "): ");
                int pos = input.nextInt();
                if (pos>nome.length - 1|pos<0){throw new IllegalArgumentException("Produto fora do vetor!");}
                System.out.println(GREEN+"\nProduto na posição " + pos + ": " + nome[pos] + " ➜ R$" + preco[pos]+RESET);
                verificador = true;
            } catch (IllegalArgumentException iae){
                System.out.println(iae);
            } catch (InputMismatchException ime){
                System.out.println("SOMENTE VALORES INTEIROS!");
                input.nextLine();
            }
        } while (!verificador);

    }
}