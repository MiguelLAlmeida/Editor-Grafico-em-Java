import java.awt.*;
public class Circulo extends Ponto {
	
	// herda o ponto central (x, y) da classe Ponto
	
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
}
