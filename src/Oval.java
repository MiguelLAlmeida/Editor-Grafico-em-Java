import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Ponto {
	int raioA,
	    raioB;
	
	public void desenha(Color corDesenho, Graphics g) {
		g.setColor(corDesenho);
		g.drawOval(getX()-raioA, getY()-raioB,  // centro - raio
				   2*raioA,2*raioB);  // centro + raio
			
	}

	public Oval()
	{
		super();
		setRaioA(0);
		setRaioB(0);
		setCor(Color.black);
	}
	
	public void setRaioA(int novoRaio) {
		raioA = novoRaio;
	}

	public void setRaioB(int novoRaio) {
		raioB = novoRaio;
	}

	public int getRaioA() {
		return raioA;
	}

	public int getRaioB() {
		return raioB;
	}

	public Oval(int xCentro, int yCentro, int novoRaioA, int novoRaioB, Color novaCor)
	{
		super(xCentro, yCentro, novaCor);  // construtor de Ponto(x,y)
		setRaioA(novoRaioA);
		setRaioB(novoRaioB);		
	}

	public String transformaString(int valor, int quantasPosicoes) {
		String cadeia = new String(valor+"");
		while (cadeia.length() < quantasPosicoes)
			cadeia = "0"+cadeia;
		return cadeia.substring(0,quantasPosicoes);
	}

	public String transformaString(String valor, int quantasPosicoes)
	{
		String cadeia = new String(valor+"");
		while (cadeia.length() < quantasPosicoes)
			cadeia = cadeia+" ";
		return cadeia.substring(0,quantasPosicoes);
	}

	public String toString()
	{
		return	transformaString("o",5)+
				transformaString(getX(),5)+
				transformaString(getY(),5)+
				transformaString(getCor().getRed(),5)+
				transformaString(getCor().getGreen(),5)+
				transformaString(getCor().getBlue(),5)+
				transformaString(getRaioA(),5)+
				transformaString(getRaioB(),5);
	}
}
