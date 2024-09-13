import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.security.SecureRandom;

public class GeradorDeSenha {

    // Componentes de interface
    private JFrame frame;
    private JTextField textFieldSenha;
    private JButton botaoGerar;
    private JButton botaoCopiar;
    private JButton botaoLimpar;
    private JTextField textFieldTamanhoSenha;

    // Caracteres para gerar a senha
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
    private static final int TAMANHO_SENHA_DEFAULT = 0; // Tamanho padrão da senha

    public GeradorDeSenha() {
        // Configuração da janela principal
        frame = new JFrame("Gerador de Senhas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300); // Tamanho da janela ajustado
        frame.setLayout(new BorderLayout());

        // Criação do painel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Campo de entrada para o tamanho da senha
        JLabel labelTamanhoSenha = new JLabel("Digite o tamanho da senha:");
        labelTamanhoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        textFieldTamanhoSenha = new JTextField(Integer.toString(TAMANHO_SENHA_DEFAULT));
        textFieldTamanhoSenha.setMaximumSize(new Dimension(200, 30));
        textFieldTamanhoSenha.setHorizontalAlignment(JTextField.CENTER);
        
        // Campo de texto para mostrar a senha gerada
        textFieldSenha = new JTextField();
        textFieldSenha.setFont(new Font("Arial", Font.BOLD, 16));
        textFieldSenha.setHorizontalAlignment(JTextField.CENTER);
        textFieldSenha.setEditable(false);
        textFieldSenha.setMaximumSize(new Dimension(380, 30));
        
        // Criação do painel de botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout());
        
        // Botão de gerar senha
        botaoGerar = new JButton("Gerar Senha");
        botaoGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarSenha();
            }
        });

        // Botão de copiar senha
        botaoCopiar = new JButton("Copiar Senha");
        botaoCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarSenha();
            }
        });

        // Botão de limpar campo
        botaoLimpar = new JButton("Limpar Campo");
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampo();
            }
        });

        // Adicionando os botões ao painel de botões
        panelBotoes.add(botaoGerar);
        panelBotoes.add(botaoCopiar);
        panelBotoes.add(botaoLimpar);

        // Adicionando componentes ao painel principal
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panelPrincipal.add(labelTamanhoSenha);
        panelPrincipal.add(textFieldTamanhoSenha);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panelPrincipal.add(textFieldSenha);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panelPrincipal.add(panelBotoes);

        // Adicionando o painel principal ao frame
        frame.add(panelPrincipal, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }

    // Método para gerar uma senha aleatória
    private void gerarSenha() {
        try {
            int tamanhoSenha = Integer.parseInt(textFieldTamanhoSenha.getText());
            if (tamanhoSenha < 1) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um número maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            SecureRandom random = new SecureRandom();
            StringBuilder senha = new StringBuilder(tamanhoSenha);
            for (int i = 0; i < tamanhoSenha; i++) {
                int index = random.nextInt(CARACTERES.length());
                senha.append(CARACTERES.charAt(index));
            }
            textFieldSenha.setText(senha.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para copiar a senha gerada para a área de transferência
    private void copiarSenha() {
        String senha = textFieldSenha.getText();
        if (!senha.isEmpty()) {
            StringSelection selecao = new StringSelection(senha);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selecao, null);
            JOptionPane.showMessageDialog(frame, "Senha copiada para a área de transferência!");
        } else {
            JOptionPane.showMessageDialog(frame, "Nenhuma senha para copiar!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método para limpar o campo de texto
    private void limparCampo() {
        textFieldSenha.setText("");
        textFieldTamanhoSenha.setText(Integer.toString(TAMANHO_SENHA_DEFAULT));
    }

    // Método principal
    public static void main(String[] args) {
        // Executando o programa
        SwingUtilities.invokeLater(() -> new GeradorDeSenha());
    }
}
