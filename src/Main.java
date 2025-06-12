import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int num = 0;
        boolean stop = false;
        do {
            do {
                try{
                    System.out.print("\n0 - Sair\n1 - Inteiros\n2 - Produtos\nEscolha uma opção: ");
                    num = input.nextInt();
                    if (num>2|num<0) throw new IllegalArgumentException("Valor fora das opções!");
                    stop = true;
                }
                catch (InputMismatchException ime){
                    System.out.println("Somente valores inteiros!!");
                    input.next();
                } catch (IllegalArgumentException iae){
                    System.out.println(iae);
                }
            } while (stop == false);


            if (num == 1){
                boolean parada = true;
                char as = '"';
                Scanner inputFile = null;
                try {
                    File file = new File("C:\\Users\\erickbarbosa-ieg\\OneDrive - Instituto J&F\\Área de Trabalho\\Tech\\LPR\\Codes\\manipulacao-arquivos\\src\\inteiros10.txt");
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException fne){
                    System.out.println("Arquivo não encontrado!");
                }

                int media = 0;
                double cont = 0;
                int cont2 = 1;

                while (inputFile.hasNextLine()){
                    parada = true;
                    String linha = null;
                    while (parada == true){
                        linha = inputFile.nextLine().trim();
                        if (linha.isEmpty()) {
                            cont2++;
                        } else parada = false;
                    }

                    System.out.println("\n\n-- Linha "+cont2+" --");
                    String[] xi = linha.split(" ");
                    for( String x: xi){
                        try{
                            media += Integer.parseInt(x);
                            if (x == xi[xi.length-1]){
                                System.out.print("|\t"+x+"\t|");
                            } else {
                                System.out.print("|\t" + x + "\t");
                            }
                            cont++;
                        } catch (NumberFormatException nfe){
                            if (x != "") {
                                System.out.print(RED+"(" + as + x + as + " - Não é um número inteiro aceito)\t\t"+RESET);
                            }
                        }
                    }
                    cont2++;
                } inputFile.close();

                try {
                    if (cont == 0) throw new IllegalArgumentException("Sem valores inteiros no arquivo!");
                    System.out.println(GREEN+"\n\nMédia ➜ "+media/cont+RESET);
                } catch (IllegalArgumentException iae){
                    System.out.println("\n\n"+iae);
                }
            }


            if (num == 2){
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
                        System.out.print("Digite a posição desejada (1 a " + (nome.length) + "): ");
                        int pos = input.nextInt();
                        if (pos>nome.length|pos<=0){throw new IllegalArgumentException("Produto fora do vetor!");}
                        System.out.println(GREEN+"\nProduto na posição " + pos + ": " + nome[pos-1] + " ➜ R$" + preco[pos-1]+RESET);
                        verificador = true;
                    } catch (IllegalArgumentException iae){
                        System.out.println(iae);
                    } catch (InputMismatchException ime){
                        System.out.println("SOMENTE VALORES INTEIROS!");
                        input.nextLine();
                    }
                } while (!verificador);
            }
        } while (num !=0);
    }
}