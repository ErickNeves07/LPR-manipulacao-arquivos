import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args){
        //Variáveis de controle para repetição do código
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
            } while (!stop);


            if (num == 1){


                // Tentativa de ler arquivo
                Scanner inputFile = null; //Inicialização da variável
                try {
                    File file = new File("C:\\Users\\erickbarbosa-ieg\\OneDrive - Instituto J&F\\Área de Trabalho\\Tech\\LPR\\Codes\\manipulacao-arquivos\\src\\inteiros10.txt");
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException fne){
                    System.out.println("Arquivo não encontrado!");
                }


                // Declaração de valores iniciais
                boolean parada;
                char aspas = '"';
                int soma = 0;
                double contMedia = 0;
                int numLinha = 1;
                String linha = null;
                String[] valoresLinha;

                // Verificação se a linha está vazia
                // - Se tiver vazia, não vai mostrar o número da linha (vai passar para próxima)
                while (inputFile.hasNextLine()){
                    parada = true;
                    while (parada){
                        linha = inputFile.nextLine().trim(); //.trim() remove os espaços extras ao redor
                        if (linha.isEmpty()) {
                            numLinha++;
                        } else parada = false;
                    }

                    //Apresenta em que linha foi encontrada determinado valor
                    System.out.println("\n\n-- Linha "+numLinha+" --");
                    valoresLinha = linha.split(" "); //Coloca os valores da linha em um vetor
                    for( String valor: valoresLinha){
                        try{
                            soma += Integer.parseInt(valor);
                            if (valor.equals(valoresLinha[valoresLinha.length-1])){
                                System.out.print("|\t"+valor+"\t|");
                            } else {
                                System.out.print("|\t" + valor + "\t");
                            }
                            contMedia++;
                        } catch (NumberFormatException nfe){ //Detecta erro de formação do inteiro no "parseInt"
                            if (!valor.equals("")) {
                                System.out.print(RED+"(" + aspas + valor + aspas + " - Não é um número inteiro aceito)\t\t"+RESET); //Não printa se o erro for ter um espaço
                            }
                        }
                    }
                    numLinha++;
                } inputFile.close();

                try {
                    if (contMedia == 0) throw new IllegalArgumentException("Sem valores inteiros no arquivo!");
                    System.out.println(GREEN+"\n\nMédia ➜ "+soma/contMedia+RESET);
                } catch (IllegalArgumentException iae){
                    System.out.println("\n\n"+iae);
                }
            }


            if (num == 2){
                //Declaração de valores
                Scanner inputFile = null;
                boolean verificador = false;
                int numLinhas =0;
                String[] nome;
                Double[] preco;

                //Abrir o arquivo para criar vetores com o tamanho correto
                try {
                    File file = new File("src/produtos.txt");
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException fne){
                    System.out.println("Arquivo não encontrado!");
                }
                while (inputFile.hasNextLine()){
                    inputFile.nextLine();
                    numLinhas++;
                }
                inputFile.close();

                //Reabrir o arquivo
                try {
                    inputFile = new Scanner(new File("src/produtos.txt"));
                } catch (FileNotFoundException fne){
                    System.out.println("Erro ao reabrir o arquivo!");
                }
                nome = new String[numLinhas];
                preco = new Double[numLinhas];

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
                        System.out.println(RED+iae+RESET);
                    } catch (InputMismatchException ime){
                        System.out.println("SOMENTE VALORES INTEIROS!");
                        input.nextLine();
                    }
                } while (!verificador);
            }
        } while (num !=0);
    }
}