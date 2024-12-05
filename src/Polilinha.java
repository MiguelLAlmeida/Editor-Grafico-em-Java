import java.awt.*;  // para acessar Color e
// m�todos de desenho
public class Polilinha extends Ponto {
    // herda (x, y) da classe Ponto, que s�o as coordenadas
    // do ponto inicial da reta; tamb�m herda a cor e, em
    // seguida define o ponto final:
    Ponto pontoFinal;
    int[] xPontos, yPontos;
    int numeroPontos;

    public Polilinha() {
        super();  // cria ponto inicial
        xPontos = null;
        yPontos = null;
        numeroPontos = 0;
        setCor(Color.black);
    }

    public Polilinha(int x1, int y1, int[] x2, int[] y2, Color novaCor) {
        super(x1,y1, novaCor);
        xPontos = x2;
        yPontos = y2;
        numeroPontos = xPontos.length;

    }

    public void desenha(Color corDesenho, Graphics g) {
        g.setColor(corDesenho);
        g.drawPolyline(xPontos, yPontos,   // lista de coordenadas (x,y)
                numeroPontos);  // quantidade de pontos que estão guardados na lista de pontos
    }
}
