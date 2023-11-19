// Classe Casa

public class Casa {
    private String cor;
    private Porta porta1;
    private Porta porta2;

    public Casa(String cor) {
        this.cor = cor;
    }

    public void pintar(String novaCor) {
        this.cor = novaCor;
        System.out.println("A casa foi pintada de " + novaCor + ".");
    }

    public void adicionarPorta(Porta porta) {
        if (porta1 == null) {
            porta1 = porta;
            System.out.println("Porta 1 adicionada à casa.");
        } else if (porta2 == null) {
            porta2 = porta;
            System.out.println("Porta 2 adicionada à casa.");
        } else {
            System.out.println("A casa já possui duas portas. Não é possível adicionar mais portas.");
        }
    }

    public void abrirPorta(int numeroPorta) {
        Porta porta = getPorta(numeroPorta);
        if (porta != null) {
            porta.abrir();
            System.out.println("Porta " + numeroPorta + " aberta.");
        }
    }

    public void fecharPorta(int numeroPorta) {
        Porta porta = getPorta(numeroPorta);
        if (porta != null) {
            porta.fechar();
            System.out.println("Porta " + numeroPorta + " fechada.");
        }
    }

    public int quantPortasAbertas() {
        int contador = 0;
        if (porta1 != null && porta1.estaAberta()) {
            contador++;
        }
        if (porta2 != null && porta2.estaAberta()) {
            contador++;
        }
        return contador;
    }

    private Porta getPorta(int numeroPorta) {
        if (numeroPorta == 1) {
            return porta1;
        } else if (numeroPorta == 2) {
            return porta2;
        } else {
            System.out.println("Número de porta inválido.");
            return null;
        }
    }

    // Classe Porta
    class Porta {
        private boolean aberta;

        public void abrir() {
            aberta = true;
        }

        public void fechar() {
            aberta = false;
        }

        public boolean estaAberta() {
            return aberta;
        }
    }

    // Programa Principal
    public static void main(String[] args) {
        Casa minhaCasa = new Casa("Branca");
        minhaCasa.pintar("Azul");

        Porta porta1 = minhaCasa.new Porta();
        Porta porta2 = minhaCasa.new Porta();

        minhaCasa.adicionarPorta(porta1);
        minhaCasa.adicionarPorta(porta2);

        minhaCasa.abrirPorta(1);
        minhaCasa.fecharPorta(2);

        int quantPortasAbertas = minhaCasa.quantPortasAbertas();
        System.out.println("Número de portas abertas: " + quantPortasAbertas);
    }
}