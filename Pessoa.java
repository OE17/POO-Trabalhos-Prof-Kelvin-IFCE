public class Pessoa {
    // Atributos
    private String nome;
    private int idade;

    // Construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Método para fazer aniversário
    public void fazAniversario() {
        idade++;
    }

    // Métodos de acesso (getters e setters) para os atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Método Principal para testar a classe
    public static void main(String[] args) {
        // Criando uma pessoa
        Pessoa pessoa = new Pessoa("João", 25);

        // Fazendo alguns aniversários
        pessoa.fazAniversario();
        pessoa.fazAniversario();

        // Imprimindo nome e idade
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Idade: " + pessoa.getIdade());
    }
}