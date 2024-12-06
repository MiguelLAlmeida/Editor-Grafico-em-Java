import java.awt.*;  // para acessar Color e
					 // m�todos de desenho
public class Linha extends Ponto {
	Ponto pontoFinal;
	
	public Linha()
	{
      super();  // cria ponto inicial
      pontoFinal = new Ponto();
	  setCor(Color.black);
	}
	
	public Linha(int x1, int y1, int x2, int y2, Color novaCor)
	{
		super(x1,y1, novaCor);
		pontoFinal = new Ponto(x2,y2, novaCor);
	}
	
	public void desenha(Color corDesenho, Graphics g)
	{
		g.setColor(corDesenho);
		g.drawLine(super.getX(),super.getY(),   // ponto inicial
				   pontoFinal.getX(), pontoFinal.getY());
	}

	 public Ponto getPontoFinal() {
		return pontoFinal;
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
				 transformaString(getPontoFinal().getX(),5)+
				 transformaString(getPontoFinal().getX(),5)+
				 transformaString(getCor().getRed(),5)+
				 transformaString(getCor().getGreen(),5)+
				 transformaString(getCor().getBlue(),5);
	 }
}
