import javax.swing.*;
import java.awt.*;
public class Esteira {

    private String estado = "Desligada";

    public Esteira() {
    }

    public void ligar() {
        this.estado = "Ligada";

        // Mostra uma mensagem de confirmação
        JOptionPane.showMessageDialog(null, "A esteira está ligada.");
    }

    public void desligar() {
        if (this.estado.equals("Ligada")) {
            this.estado = "Desligada";

            // Mostra uma mensagem de confirmação
            JOptionPane.showMessageDialog(null, "A esteira foi desligada.");
        }
    }

    public String getEstado() {
        return this.estado;
    }

    public static void main(String[] args) {
        // Crie uma janela
        JFrame frame = new JFrame("Esteira");
        frame.setLayout(new FlowLayout());

        // Crie um painel para os botões
        JPanel buttonPanel = new JPanel();

        // Adicione um botão para ligar a esteira
        JButton ligarButton = new JButton("Ligar");
        buttonPanel.add(ligarButton);

        // Adicione um botão para desligar a esteira
        JButton desligarButton = new JButton("Desligar");
        buttonPanel.add(desligarButton);

        // Adicione um botão para sair
        JButton sairButton = new JButton("Sair");
        buttonPanel.add(sairButton);

        Esteira esteira = new Esteira();

        // Vincule os botões aos seus eventos
        ligarButton.addActionListener(e -> {
            esteira.ligar();
        });

        desligarButton.addActionListener(e -> {
            esteira.desligar();
        });

        sairButton.addActionListener(e -> {
            frame.dispose();
        });

        // Adicione o painel de botões ao quadro
        frame.add(buttonPanel);

        // Configura a janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}