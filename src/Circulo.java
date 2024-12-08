import java.awt.*;
public class Circulo extends Ponto {

	int raio;
	Color cor;
	
	public void desenha(Color corDesenho, Graphics g) {
		g.setColor(corDesenho);
		g.drawOval(getX()-raio, getY()-raio,  // centro - raio
				   2*raio,2*raio);  // centro + raio
	}

	public Circulo()
	{
		super();
		setRaio(0);
		setCor(Color.black);
	}

	public int getRaio() {
		return raio;
	}
	
	public void setRaio(int novoRaio) {
		raio = novoRaio;
	}
	
	public void setCor(Color novaCor) {
		cor = novaCor;
	}
	
	public Circulo(int xCentro, int yCentro, int novoRaio, Color novaCor)
	{
		super(xCentro, yCentro, novaCor);  // construtor de Ponto(x,y)
		setRaio(novoRaio);
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
				transformaString(getRaio(),5);
	}
}
