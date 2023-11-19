import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FabricaDeGarrafas {

    private int tempoDesligarEsteira = 300000; // Tempo em milissegundos (5 minutos)
    private CaixaDeGarrafas caixa;
    private Esteira esteira;
    private JLabel quantidadeGarrafasLabel;

    public FabricaDeGarrafas() {
        caixa = new CaixaDeGarrafas();
        esteira = new Esteira();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FabricaDeGarrafas factory = new FabricaDeGarrafas();
            factory.createUI();
        });
    }

    private void createUI() {
        JFrame frame = new JFrame("Fábrica de Garrafas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();

        JComboBox<String> tipoGarrafaComboBox = new JComboBox<>();
        tipoGarrafaComboBox.addItem("Vidro");
        tipoGarrafaComboBox.addItem("Plástico");

        JComboBox<String> conteudoGarrafaComboBox = new JComboBox<>();
        conteudoGarrafaComboBox.addItem("Água");
        conteudoGarrafaComboBox.addItem("Suco");

        quantidadeGarrafasLabel = new JLabel("Quantidade de Garrafas: " + caixa.getQuantGarrafas());
        buttonPanel.add(quantidadeGarrafasLabel);

        addBotao(buttonPanel, "Adicionar Garrafas", e -> adicionarGarrafasECalcularQuantidade());
        addBotao(buttonPanel, "Ligar Esteira", e -> ligarEsteira());
        addBotao(buttonPanel, "Desligar Esteira", e -> esteira.desligar());
        addBotao(buttonPanel, "Configurar Garrafa", e -> {
            // Configuração do tipo e conteúdo da garrafa
            String tipoGarrafa = tipoGarrafaComboBox.getSelectedItem().toString();
            String conteudoGarrafa = conteudoGarrafaComboBox.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "Tipo de garrafa: " + tipoGarrafa + ", Conteúdo: " + conteudoGarrafa);
        });
        addBotao(buttonPanel, "Definir Tempo do Temporizador", e -> definirTempoTemporizador());
        addBotao(buttonPanel, "Iniciar Temporizador para Desligar Esteira", e -> iniciarTemporizadorParaDesligarEsteira());
        addBotao(buttonPanel, "Entregar ao Caminhão", e -> entregarAoCaminhao());

        buttonPanel.add(tipoGarrafaComboBox);
        buttonPanel.add(conteudoGarrafaComboBox);
        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    private void addBotao(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void adicionarGarrafasECalcularQuantidade() {
        caixa.addGarrafas(1);
        quantidadeGarrafasLabel.setText("Quantidade de Garrafas: " + caixa.getQuantGarrafas());
    }

    private void ligarEsteira() {
        if (caixa.getQuantGarrafas() == caixa.getCapacidadeCaixa()) {
            esteira.ligar();
            iniciarTemporizadorParaDesligarEsteira();
            JOptionPane.showMessageDialog(null, "A esteira está entregando garrafas.");
        } else {
            JOptionPane.showMessageDialog(null, "A caixa não está cheia.");
        }
    }

    private void definirTempoTemporizador() {
        String input = JOptionPane.showInputDialog("Digite o tempo do temporizador (em minutos):");
        try {
            int minutos = Integer.parseInt(input);
            tempoDesligarEsteira = minutos * 60 * 1000; // Converter minutos em milissegundos
            JOptionPane.showMessageDialog(null, "Tempo do temporizador definido para " + minutos + " minutos (" + tempoDesligarEsteira + " ms).");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tempo inválido. O tempo do temporizador permanecerá inalterado.");
        }
    }

    private void iniciarTemporizadorParaDesligarEsteira() {
        if (tempoDesligarEsteira <= 0) {
            JOptionPane.showMessageDialog(null, "O tempo do temporizador deve ser maior que zero.");
        } else {
            if (esteira.isLigada()) {
                esteira.desligarAutomaticamente(tempoDesligarEsteira);
                JOptionPane.showMessageDialog(null, "O temporizador está ativo. A esteira será desligada após " + tempoDesligarEsteira + " ms.");
            } else {
                JOptionPane.showMessageDialog(null, "A esteira está desligada. Ligue a esteira antes de iniciar o temporizador.");
            }
        }
    }

    private void entregarAoCaminhao() {
        if (esteira.isLigada()) {
            esteira.desligarAutomaticamente(tempoDesligarEsteira);
            JOptionPane.showMessageDialog(null, "Entregando ao caminhão...");
            esteira.desligar();
        } else {
            JOptionPane.showMessageDialog(null, "A esteira está desligada. Ligue a esteira antes de entregar ao caminhão.");
        }
    }
}

class CaixaDeGarrafas {
    private int quantGarrafas;
    private int capacidadeCaixa = 3;

    public CaixaDeGarrafas() {
        this.quantGarrafas = 0;
    }

    public void addGarrafas(int quantidade) {
        quantGarrafas += quantidade;
        if (quantGarrafas >= capacidadeCaixa) {
            quantGarrafas = capacidadeCaixa;
        }
    }

    public int getQuantGarrafas() {
        return quantGarrafas;
    }

    public int getCapacidadeCaixa() {
        return capacidadeCaixa;
    }
}

class Esteira {
    private boolean ligada = false;
    private Timer timer;

    public void ligar() {
        ligada = true;
        JOptionPane.showMessageDialog(null, "A esteira está ligada.");
    }

    public void desligar() {
        ligada = false;
        if (timer != null) {
            timer.stop();
        }
        JOptionPane.showMessageDialog(null, "A esteira foi desligada.");
    }

    public boolean isLigada() {
        return ligada;
    }

    public void desligarAutomaticamente(int tempo) {
        if (ligada) {
            timer = new Timer(tempo, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    desligar();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}