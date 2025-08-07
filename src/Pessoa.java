public class Pessoa {
    private String nome;
    private long cpf;
    private int idade;

    public Pessoa(String nome, long cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    } public long getCpf() {
        return cpf;
    } public int getIdade() {
        return idade;
    }

    public String toString() {
        return nome+" "+cpf+" "+idade;
    }
}
