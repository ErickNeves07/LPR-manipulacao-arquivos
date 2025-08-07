import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        //Variáveis de controle para repetição do código
        Scanner input = new Scanner(System.in);
        int num = 0;
        boolean stop = false;
        do {
            do {
                try {
                    System.out.print("\n0 - Sair\n1 - Inteiros\n2 - Produtos\n3 - Salário (ler e projetar aumento)\n4 - Pessoas\n5 - Ordenar alfabéticamente\n\nEscolha uma opção: ");
                    num = input.nextInt();
                    if (num > 5 | num < 0) throw new IllegalArgumentException("Valor fora das opções!");
                    stop = true;
                } catch (InputMismatchException ime) {
                    System.out.println("Somente valores inteiros!!");
                    input.next();
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae);
                }
            } while (!stop);


            if (num == 1) {
                // Tentativa de ler arquivo
                Scanner inputFile = null; //Inicialização da variável
                try {
                    File file = new File("src/inteiros10.txt");
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException fne) {
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
                while (inputFile.hasNextLine()) {
                    parada = true;
                    while (parada) {
                        linha = inputFile.nextLine().trim(); //.trim() remove os espaços extras ao redor
                        if (linha.isEmpty()) {
                            numLinha++;
                        } else parada = false;
                    }

                    //Apresenta em que linha foi encontrada determinado valor
                    System.out.println("\n\n-- Linha " + numLinha + " --");
                    valoresLinha = linha.split(" "); //Coloca os valores da linha em um vetor
                    for (int i = 0; i < valoresLinha.length; i++) {
                        try {
                            soma += Integer.parseInt(valoresLinha[i]);
                            if (i == valoresLinha.length - 1) {
                                System.out.print("|\t" + valoresLinha[i] + "\t|");
                            } else {
                                System.out.print("|\t" + valoresLinha[i] + "\t");
                            }
                            contMedia++;
                        } catch (NumberFormatException nfe) { //Detecta erro de formação do inteiro no "parseInt"
                            if (!valoresLinha[i].equals("")) {
                                System.out.print(RED + "(" + aspas + valoresLinha[i] + aspas + " - Não é um número inteiro aceito)\t\t" + RESET); //Não printa se o erro for ter um espaço
                            }
                        }
                    }
                    numLinha++;
                }
                inputFile.close();

                try {
                    if (contMedia == 0)
                        throw new IllegalArgumentException(RED + "Sem valores inteiros no arquivo!" + RESET);
                    System.out.println(GREEN + "\n\nMédia ➜ " + soma / contMedia + RESET);
                } catch (IllegalArgumentException iae) {
                    System.out.println("\n\n" + iae);
                }
            }


            else if (num == 2) {
                //Declaração de valores
                Scanner inputFile = null;
                boolean verificador = false;
                int numLinhas = 0;
                String[] nome;
                Double[] preco;

                //Abrir o arquivo para criar vetores com o tamanho correto
                try {
                    File file = new File("src/produtos.txt");
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException fne) {
                    System.out.println("Arquivo não encontrado!");
                }
                while (inputFile.hasNextLine()) {
                    inputFile.nextLine();
                    numLinhas++;
                }
                inputFile.close();

                //Reabrir o arquivo
                try {
                    inputFile = new Scanner(new File("src/produtos.txt"));
                } catch (FileNotFoundException fne) {
                    System.out.println("Erro ao reabrir o arquivo!");
                }
                nome = new String[numLinhas];
                preco = new Double[numLinhas];

                for (int contador = 0; contador < nome.length; contador++) {
                    nome[contador] = inputFile.next();
                    while (!inputFile.hasNextDouble()) {
                        nome[contador] = nome[contador] + " ".concat(inputFile.next());
                    }
                    preco[contador] = inputFile.nextDouble();
                }
                inputFile.close();

                do {
                    try {
                        System.out.print("Digite a posição desejada (1 a " + (numLinhas) + "): ");
                        int pos = input.nextInt();
                        if (pos > nome.length | pos <= 0) {
                            throw new IllegalArgumentException("Produto fora do vetor!");
                        }
                        System.out.println(GREEN + "\nProduto na posição " + pos + ": " + nome[pos - 1] + " ➜ R$" + preco[pos - 1] + RESET);
                        verificador = true;
                    } catch (IllegalArgumentException iae) {
                        System.out.println(RED + iae + RESET);
                    } catch (InputMismatchException ime) {
                        System.out.println("SOMENTE VALORES INTEIROS!");
                        input.nextLine();
                    }
                } while (!verificador);

            }
            else if (num == 3) {
                int qntd = 0;
                Scanner inputFile = null;
                int[] vetor;

                //Abrir o arquivo para criar vetores com o tamanho correto
                try {
                    File file = new File("src/salario.txt");
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException fne) {
                    System.out.println("Arquivo não encontrado!");
                }
                while (inputFile.hasNextInt()) {
                    inputFile.nextInt();
                    qntd++;
                }
                inputFile.close();

                //Vetor com o tamanho certo
                vetor = new int[qntd];

                //Reabrir o arquivo
                try {
                    inputFile = new Scanner(new File("src/salario.txt"));
                } catch (FileNotFoundException fne) {
                    System.out.println("Erro ao reabrir o arquivo!");
                }

                //Armazenar no vetor
                for (int i = 0; i < vetor.length; i++) {
                    vetor[i] = inputFile.nextInt();
                }

                //Fechar o arquivo
                inputFile.close();

                try { // `true` para anexar ao arquivo
                    FileWriter writer = new FileWriter("src/salarioAtualizado.txt", true);
                    PrintWriter pw = new PrintWriter(writer);
                    System.out.print("\nQual o percentual de aumento (em %)? ");
                    input.nextLine();
                    int percentual = input.nextInt() / 100;
                    for (int i : vetor) {
                        pw.println(i * (1 + percentual));
                    }
                    System.out.println("Mensagens registradas com sucesso!");
                    pw.close();
                } catch (IOException ioe) {
                    System.err.println("Ocorreu um erro ao escrever no arquivo: " + ioe.getMessage());
                }
            }
            else if (num == 4) {
                //Inicialização de variáveis
                String nome;
                Scanner leitorNomes = null;
                Scanner leitorCPF = null;
                Scanner leitorIdades = null;
                Pessoa[] vetor = new Pessoa[10];
                int mediaIdade = 0;

                try {
                    leitorNomes = new Scanner(new File("src/nomes.txt"));
                } catch (FileNotFoundException fne) {
                    System.out.println("Erro ao abrir o arquivo!");
                }

                try {
                    leitorCPF = new Scanner(new File("src/cpf.txt"));
                } catch (FileNotFoundException fne) {
                    System.out.println("Erro ao abrir o arquivo!");
                }

                try {
                    leitorIdades = new Scanner(new File("src/idades.txt"));
                } catch (FileNotFoundException fne) {
                    System.out.println("Erro ao abrir o arquivo!");
                }

                //Processamento
                for (int i = 0; i < 10; i++) {
                    vetor[i] = new Pessoa(leitorNomes.nextLine(), leitorCPF.nextLong(), leitorIdades.nextInt());
                }
                for (Pessoa p : vetor) {
                    mediaIdade += p.getIdade();
                }
                System.out.println("\nMédia de idade ➜ " + mediaIdade / vetor.length);

                //Fechar os arquivos
                leitorNomes.close();
                leitorCPF.close();
                leitorIdades.close();

                //Apresentar em outro arquivo
                PrintWriter pw = null;
                try {
                    FileWriter filewritwer = new FileWriter("src/pessoas.txt");
                    pw = new PrintWriter(filewritwer);
                    System.out.println("Mensagem registrada com sucesso!");
                } catch (IOException ioe) {
                    System.err.println("Erro ao abrir o arquivo de registro: " + ioe.getMessage());
                }
                for (Pessoa p : vetor) {
                    pw.println(p.toString());
                }
                pw.close();
            }

            if (num == 5) {
                String nome;
                Pessoa[] vetor = new Pessoa[10];

                // Tentativa de ler arquivo
                Scanner inputFile = null; //Inicialização da variável
                try {
                    File file = new File("src/pessoas.txt");
                    inputFile = new Scanner(file);
                } catch (FileNotFoundException fne) {
                    System.out.println("Arquivo não encontrado!");
                }

                // Armazenamento em vetor
                for(int i = 0; i<10; i++){
                    nome = inputFile.next();
                    while(!inputFile.hasNextLong()){
                        nome = nome.concat(inputFile.next());
                    }
                    vetor[i] = new Pessoa(nome, inputFile.nextLong(), inputFile.nextInt());
                }

                //Fechar o arquivo, após todas as informações serem registradas
                inputFile.close();

                //Ordenar vetor
                Ordenar(vetor);

                //Apresentar no mesmo arquivo
                PrintWriter pw = null;
                try {
                    FileWriter filewritwer = new FileWriter("src/pessoas.txt");
                    pw = new PrintWriter(filewritwer);
                } catch (IOException ioe) {
                    System.err.println("Erro ao abrir o arquivo de registro: " + ioe.getMessage());
                }
                for (Pessoa p : vetor) {
                    pw.println(p.toString());
                }
                pw.close();

                //Apresentar resultado
                System.out.println("Mensagem registrada com sucesso!");
            }
        } while (num != 0);
    }

    public static void Ordenar(Pessoa[] vetor){
        int posicaoTrocada;
        Pessoa apoio = null;
            for( int cont1 = 0; cont1< vetor.length; cont1++){
                posicaoTrocada = verificarAlfabeto(vetor, cont1);
                apoio = vetor[posicaoTrocada];
                vetor[posicaoTrocada] = vetor[cont1];
                vetor[cont1] = apoio;
                if(posicaoTrocada > cont1){
                    cont1--;
                }
            }
    }

    public static int verificarAlfabeto(Pessoa[] vetor, int contador){
        String alfabeto = " aáàâãbcçdeéèêfghiíìîjklmnoóòôõpqrstuúùûvwxyzü";
        int maior = 0;
        boolean iguais = true;

        for(int cont1 = 0; cont1<vetor[contador].getNome().length(); cont1++){
            if(iguais){
                iguais = false;
                for (int cont2 = 0; cont2<vetor.length; cont2++){
                    if (cont1 < vetor[cont2].getNome().length()){   
                        if(alfabeto.indexOf((vetor[contador].getNome().substring(cont1, cont1+1)).toLowerCase()) > alfabeto.indexOf(vetor[cont2].getNome().substring(cont1, cont1 + 1).toLowerCase())){
                            if(cont2>maior){
                                maior=cont2;
                            }
                        } else if(alfabeto.indexOf((vetor[contador].getNome().substring(cont1, cont1+1)).toLowerCase()) == alfabeto.indexOf(vetor[cont2].getNome().substring(cont1, cont1 + 1).toLowerCase())){
                            iguais = true;
                        }
                    }
                }
            }
        }
        return maior;
    }
}

//Leitor de nomes compostos no 4 (arrumar o espaço)
//Lógica de ordenação alfabética
