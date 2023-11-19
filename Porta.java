public class Porta {
    private boolean aberta;
    private String cor;
    private double altura;
    private double comprimento;

    // Construtor
    public Porta(String cor, double altura, double comprimento) {
        this.aberta = false;
        this.cor = cor;
        this.altura = altura;
        this.comprimento = comprimento;
    }

    // Métodos
    public void abrir() {
        if (!aberta) {
            System.out.println("A porta foi aberta.");
            aberta = true;
        } else {
            System.out.println("A porta já está aberta.");
        }
    }

    public void fechar() {
        if (aberta) {
            System.out.println("A porta foi fechada.");
            aberta = false;
        } else {
            System.out.println("A porta já está fechada.");
        }
    }

    public void pintar(String novaCor) {
        System.out.println("A porta foi pintada de " + novaCor + ".");
        cor = novaCor;
    }

    public void alterarDimensoes(double novaAltura, double novoComprimento) {
        this.altura = novaAltura;
        this.comprimento = novoComprimento;
        System.out.println("Dimensões da porta foram alteradas para Altura: " + novaAltura + ", Comprimento: " + novoComprimento);
    }

    public boolean estaAberta() {
        return aberta;
    }

    // Programa Principal (main)
    public static void main(String[] args) {
        Porta minhaPorta = new Porta("Marrom", 2.0, 0.8);

        minhaPorta.abrir();
        minhaPorta.fechar();

        minhaPorta.pintar("Azul");

        minhaPorta.alterarDimensoes(2.5, 1.0);

        minhaPorta.pintar("Verde");  

        System.out.println("Altura da porta: " + minhaPorta.altura);
        System.out.println("Comprimento da porta: " + minhaPorta.comprimento);

        if (minhaPorta.estaAberta()) {
            System.out.println("A porta está aberta.");
        } else {
            System.out.println("A porta está fechada.");
        }
    }
}
