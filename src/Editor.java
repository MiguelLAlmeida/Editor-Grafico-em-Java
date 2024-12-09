import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Editor extends JFrame
{
	private JButton btnPonto, btnLinha, btnCirculo, btnOval, btnRetangulo, btnPolilinha, btnCores,
	btnAbrir, btnSalvar, btnApagar, btnSair, btnSelecionar, btnMudarCor;
	static private JPanel pnlBotoes;
	static private JDesktopPane panDesenho;
	static private JInternalFrame frame;
	static private MeuJPanel pnlDesenho;

	private static File arquivo;
	private static Ponto[] figuras = new Ponto[20];
	private static int[] figurasSelecionadas = new int[20];
	static int qtasFiguras, qtasSelecionadas;
	private JLabel statusBar1, statusBar2;
	static boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaCentroCirculo,esperaRaioCirculo, esperaIniOval, esperaFimOval, esperaIniRec, esperaLargEAltRec, esperaIniPolilinha;
	static private Color corAtual = Color.black;
	private static Ponto p1 = new Ponto();

	public Editor()	// construtor de Editor que criará o JFrame, colocará seu título,
	{				// estabelecerá um tamanho para o formulário e o exibirá
		super("Editor Gráfico");	// cria o JFrame e coloca um título

		// cria os botões do editor

		Icon imgAbrir = new ImageIcon("documents/images/abrir.jpg");
		btnAbrir = new JButton("Abrir", imgAbrir);
		btnSalvar = new JButton("Salvar", new ImageIcon("documents/images/salvar.jpg"));
		btnPonto = new JButton("Ponto", new ImageIcon("documents/images/ponto.jpg"));
		btnLinha = new JButton("Linha", new ImageIcon("documents/images/linha.jpg"));
		btnCirculo = new JButton("Círculo", new ImageIcon("documents/images/circulo.jpg"));
		btnOval = new JButton("Elipse", new ImageIcon("documents/images/elipse.jpg"));
		btnRetangulo = new JButton("Retângulo", new ImageIcon("documents/images/retangulo.jpg"));
		btnPolilinha = new JButton("Polilinha", new ImageIcon("documents/images/polilinha.jpg"));
		btnSelecionar = new JButton("Selecionar", new ImageIcon("documents/images/Selecionar"));
		btnMudarCor = new JButton("Mudar Cor Selecionadas");
		btnCores = new JButton("Cores", new ImageIcon("documents/images/cores.jpg"));
		btnApagar = new JButton("Apagar", new ImageIcon("documents/images/apagar.jpg"));
		btnSair = new JButton("Sair", new ImageIcon("documents/images/sair.jpg"));

		// cria o JPanel que armazenará os botões

		pnlBotoes = new JPanel();

		// cria o layout usado para dispor fisicamente os botões		
		FlowLayout flwBotoes = new FlowLayout();

		//	 informa que os componentes do pnlBotoes serão dispostos em forma livre
		pnlBotoes.setLayout(flwBotoes);

		// adiciona os controles visuais (botões) ao painel de botões, de cima para
		// baixo, da esquerda para direita. 

		pnlBotoes.add(btnAbrir);
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnPonto);
		pnlBotoes.add(btnLinha);
		pnlBotoes.add(btnCirculo);
		pnlBotoes.add(btnOval);
		pnlBotoes.add(btnRetangulo);
		pnlBotoes.add(btnPolilinha);
		pnlBotoes.add(btnSelecionar);
		pnlBotoes.add(btnCores);
		pnlBotoes.add(btnMudarCor);
		pnlBotoes.add(btnApagar);
		pnlBotoes.add(btnSair);

		setSize(700,500);	// tamanho do formulário em pixels
		setVisible(true);		// exibe o formulário

		Container cntForm = getContentPane(); // acessa o painel de conteúdo do frame
		cntForm.setLayout(new BorderLayout());
		cntForm.add(pnlBotoes , BorderLayout.NORTH);

		panDesenho = new JDesktopPane();
		cntForm.add(panDesenho);
		frame = new JInternalFrame("Nenhum arquivo aberto", true, true, true, true);
		panDesenho.add(frame);
		frame.setSize(this.getWidth() / 2,this.getHeight() / 2);
		frame.show();
    	frame.setOpaque(true);

		Container cntFrame = frame.getContentPane();
		pnlDesenho = new MeuJPanel();
		cntFrame.add(pnlDesenho);

		btnAbrir.addActionListener(new FazAbertura());
		btnPonto.addActionListener(new DesenhaPonto());
		btnLinha.addActionListener(new DesenhaReta());
		btnOval.addActionListener(new DesenhaOval());
		btnCirculo.addActionListener(new DesenhaCirculo());
		btnRetangulo.addActionListener(new DesenhaRetangulo());
		btnPolilinha.addActionListener(new DesenhaPolilinha());
		btnCores.addActionListener(new SolicitaCores());
		btnSelecionar.addActionListener(new Selecionar());
		btnMudarCor.addActionListener(new MudaCorSelecionadas());
		btnSalvar.addActionListener(new FazGravacao());

		qtasSelecionadas = 0;
	}

	public static void desenhaObjetos()
	{
	   pnlDesenho.paintComponent(pnlDesenho.getGraphics());

	}

	public static void main(String[] args) {
		Editor aplicacao = new Editor();
		aplicacao.addWindowListener
		(
			new WindowAdapter ()    //  cria instância da interface
			{
				public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
			}
		);
	}

	private class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener {
		JPanel pnlStatus = new JPanel();

		public void paintComponent(Graphics g)
		{
			for (int qualFigura =0 ; qualFigura < qtasFiguras; qualFigura++)
				figuras[qualFigura].desenha(figuras[qualFigura].getCor(), g);
		}

		public void mouseClicked (MouseEvent e)
		{
			statusBar1.setText("Mensagem:");
		}

		public void mousePressed (MouseEvent e)
		{
			if (esperaPonto)
			{
				figuras[qtasFiguras] =new Ponto(e.getX(), e.getY(), corAtual);
				figuras[qtasFiguras].desenha(figuras[qtasFiguras].getCor(), pnlDesenho.getGraphics());
				qtasFiguras++;
				esperaPonto = false;
				revalidate();
			}
			else if (esperaInicioReta)
			{
				p1.setCor(corAtual);
				p1.setX(e.getX());
				p1.setY(e.getY());
				esperaInicioReta = false;
				esperaFimReta = true;
				statusBar1.setText("Mensagem: clique o ponto final da reta");
			}
			else if (esperaFimReta)
			{
				esperaInicioReta = false;
				esperaFimReta = false;
				figuras[qtasFiguras] =new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual);
				figuras[qtasFiguras].desenha(figuras[qtasFiguras].getCor(), pnlDesenho.getGraphics());
				qtasFiguras++;
				revalidate();
			}
			else if (esperaIniOval){
				p1.setCor(corAtual);
				p1.setX(e.getX());
				p1.setY(e.getY());
				esperaIniOval = false;
				esperaFimOval = true;
				statusBar1.setText("Mensagem: clique na outra extremidade do Oval");
			}
			else if (esperaFimOval){
				int raioA = (e.getX() - p1.getX()) / 2;
				int raioB = (e.getY() - p1.getY()) / 2;
				int centroX = (p1.getX() + e.getX()) / 2;
				int centroY = (p1.getY() + e.getY()) / 2;

				esperaFimOval = false;
				figuras[qtasFiguras] = new Oval(centroX, centroY, raioA, raioB, corAtual);
				figuras[qtasFiguras].desenha(figuras[qtasFiguras].getCor(), pnlDesenho.getGraphics());
				qtasFiguras++;
				revalidate();
			}
			else if (esperaCentroCirculo){
				p1.setCor(corAtual);
				p1.setX(e.getX());
				p1.setY(e.getY());
				esperaCentroCirculo = false;
				esperaRaioCirculo = true;
				statusBar1.setText("Mensagem: clique o ponto final da reta");
			}
			else if (esperaRaioCirculo){
				int raio = p1.getX() - e.getX();
				if (p1.getX() < e.getX()){
					raio = e.getX() - p1.getX();
				}
				figuras[qtasFiguras] = new Circulo(p1.getX(), p1.getY(), raio , corAtual);
				figuras[qtasFiguras].desenha(figuras[qtasFiguras].getCor(), pnlDesenho.getGraphics());
				qtasFiguras++;
				esperaRaioCirculo = false;
				revalidate();
			}
			else if (esperaIniRec){
				p1.setCor(corAtual);
				p1.setX(e.getX());
				p1.setY(e.getY());
				esperaIniRec = false;
				esperaLargEAltRec = true;
				statusBar1.setText("Mensagem: clique até onde a largura do retângulo vai");

			}
			else if (esperaLargEAltRec){
				int largura = p1.getX() - e.getX();
				if (p1.getX() < e.getX()){
					largura = e.getX() - p1.getX();
				}
				esperaIniRec = false;
				esperaLargEAltRec = true;
				statusBar1.setText("Mensagem: clique até onde a altura do retângulo vai");

				int altura = p1.getY() - e.getY();
				if (p1.getY() < e.getY()){
					largura = e.getY() - p1.getY();
				}
				figuras[qtasFiguras] = new Retangulo(p1.getX(), p1.getY(), largura , altura, corAtual);
				figuras[qtasFiguras].desenha(figuras[qtasFiguras].getCor(), pnlDesenho.getGraphics());
				qtasFiguras++;
				esperaRaioCirculo = false;
				revalidate();
			}
			else if (esperaIniPolilinha){
				p1.setCor(corAtual);
				p1.setX(e.getX());
				p1.setY(e.getY());
				esperaIniRec = false;
				esperaLargEAltRec = true;
				statusBar1.setText("Mensagem: clique até onde a largura do retângulo vai");
			}

		}
		public MeuJPanel()
		{
			super();
			addMouseListener(this);
			addMouseMotionListener(this);
			pnlStatus.setLayout(new GridLayout(1,2));
			statusBar1 = new JLabel("Mensagem");
			statusBar2 = new JLabel("Coordenada");
			pnlStatus.add(statusBar1);
			pnlStatus.add(statusBar2);
			getContentPane().add(pnlStatus, BorderLayout.SOUTH);
		}
		public void mouseEntered (MouseEvent e)
		{
			// não faz nada por enquanto
		}

		public void mouseExited (MouseEvent e)
		{
			// não faz nada por enquanto
		}

		public void mouseReleased (MouseEvent e)
		{
			// não faz nada por enquanto
		}

		public void mouseDragged(MouseEvent e)
		{

		}
		public void mouseMoved(MouseEvent e)
		{
			statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
		}
	}

	private class FazGravacao implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser arqEscolhido = new JFileChooser ();
			arqEscolhido.setDialogTitle("Abrir");
			int result = arqEscolhido.showOpenDialog(Editor.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				arquivo = arqEscolhido.getSelectedFile();
				try
				{
					if (arquivo != null){
						BufferedWriter arqFiguras = new BufferedWriter(new FileWriter(arquivo.getName()));
						try {
							for (int i = 0; i < qtasFiguras; i++){
								System.out.println(i+1);
								System.out.println(figuras[i].toString());
								arqFiguras.write(figuras[i].toString() + "\n");
							}
							arqFiguras.close();
						}
						catch (IOException ioe) {
							System.out.println("Erro de gravação no arquivo");
						}
					}
				}
				catch (IOException ex)
				{
					System.out.println("Arquivo não pôde ser aberto");
				}
			}
		}
	}

	private class FazAbertura implements ActionListener {
		public void actionPerformed(ActionEvent e)	// código executado no evento
		{
			JFileChooser arqEscolhido = new JFileChooser ();
			arqEscolhido.setDialogTitle("Abrir");
			int result = arqEscolhido.showOpenDialog(Editor.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				arquivo = arqEscolhido.getSelectedFile();
				try
				{
					BufferedReader arqFiguras = new BufferedReader(new FileReader(arquivo.getAbsolutePath()));
					try
					{
						qtasFiguras = 0;
						String linha = arqFiguras.readLine();
						while (linha != null)
						{
							System.out.println(linha);
							String tipo = linha.substring(0,5).trim();
							int xBase = Integer.parseInt(linha.substring(5,10).trim());
							int yBase = Integer.parseInt(linha.substring(10,15).trim());
							int corR = Integer.parseInt(linha.substring(15,20).trim());
							int corG = Integer.parseInt(linha.substring(20,25).trim());
							int corB = Integer.parseInt(linha.substring(25,30).trim());
							Color cor = new Color(corR, corG, corB);
							switch (tipo.charAt(0))
							{
								case 'p' :
									 figuras[qtasFiguras] = new Ponto(xBase, yBase, cor);
									break;
								case 'l' :
									int xFinal =Integer.parseInt(linha.substring(30,35).trim());
									int yFinal =Integer.parseInt(linha.substring(35,40).trim());
									 figuras[qtasFiguras] = new Linha(xBase, yBase, xFinal, yFinal, cor);
									break;
								case 'c' :
									int raio =Integer.parseInt(linha.substring(30,35).trim());
									figuras[qtasFiguras] = new Circulo(xBase, yBase, raio, cor);
									break;
								case 'o' :
									int raioA =Integer.parseInt(linha.substring(30,35).trim());
									int raioB =Integer.parseInt(linha.substring(35,40).trim());
									figuras[qtasFiguras] = new Oval(xBase, yBase, raioA, raioB, cor);
									break;
							}
							qtasFiguras++;
							linha = arqFiguras.readLine();
						}
						arqFiguras.close();

						frame.setTitle(arquivo.getName());
						desenhaObjetos();

					}
					catch (IOException ioe)
					{
						System.out.println("Erro de leitura no arquivo");
					}
				}
				catch (FileNotFoundException ex)
				{
					System.out.println("Arquivo não pôde ser aberto");
				}
			}
		}
	}
	private void limpaEsperas() {
		esperaPonto = false;
		esperaInicioReta = false;
		esperaFimReta = false;
		esperaCentroCirculo = false;
		esperaRaioCirculo = false;
		esperaIniOval = false;
		esperaFimOval = false;
		esperaIniRec = false;
		esperaLargEAltRec = false;
		esperaIniPolilinha = false;

	}

	private class DesenhaPonto implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			statusBar1.setText("Mensagem: clique o local do ponto desejado");
			limpaEsperas();
			esperaPonto = true;
		}
	}

	private class DesenhaReta implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			statusBar1.setText("Mensagem: clique o ponto inicial da reta");
			limpaEsperas();
			esperaInicioReta = true;
		}
	}
	private class DesenhaOval implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			statusBar1.setText("Mensagem: clique o centro da figura Oval");
			limpaEsperas();
			esperaIniOval = true;
		}
	}
	private class DesenhaCirculo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			statusBar1.setText("Mensagem: clique o centro do círculo");
			limpaEsperas();
			esperaCentroCirculo = true;
		}
	}
	private class DesenhaRetangulo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			statusBar1.setText("Mensagem: clique o início do retângulo");
			limpaEsperas();
			esperaIniRec = true;
		}
	}
	private class DesenhaPolilinha implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			statusBar1.setText("Mensagem: clique o início da polilinha");
			limpaEsperas();
			esperaIniPolilinha = true;
		}
	}
	private class SolicitaCores implements ActionListener{
		public void actionPerformed(ActionEvent e){
			corAtual = JColorChooser.showDialog(null, "Selecione uma cor", Color.black);
		}
	}
	private class Selecionar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			statusBar1.setText("Mensagem: Selecione um índice do vetor de Figuras");
			String input = JOptionPane.showInputDialog(Editor.this, "Digite o índice do vetor de Figuras para selecionar:", "Seleção de Índice", JOptionPane.QUESTION_MESSAGE);
			int indice = Integer.parseInt(input.trim());
			if (indice >= 0 && indice < qtasFiguras){
				Graphics2D g2d = (Graphics2D) pnlDesenho.getGraphics(); // Código pego em https://www.guj.com.br/t/ajuda-com-drawline/26784
				g2d.setStroke(new BasicStroke(2));
				figuras[indice].desenha(figuras[indice].getCor(), g2d);
				figurasSelecionadas[qtasSelecionadas] = indice;
				qtasSelecionadas++;
			}
			else
				statusBar1.setText("Mensagem: Índice Inválido");
		}
	}
	private class MudaCorSelecionadas implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			statusBar1.setText("Mensagem: Selecione a Cor que deseja mudar as figuras selecionadas");
			new SolicitaCores();
			for (int i = 0; i < qtasSelecionadas; i++){
				figuras[figurasSelecionadas[i]].setCor(corAtual);
				repaint();
				revalidate();
			}
		}
	}
}


