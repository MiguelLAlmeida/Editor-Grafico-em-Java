import java.awt.*;
public class Polilinha extends Ponto {
    // herda (x, y) da classe Ponto, que s�o as coordenadas
    // do ponto inicial da reta; tambem herda a cor
    int[] xPontos, yPontos;
    int numeroPontos;

    public Polilinha() {
        super();  // cria ponto inicial
        xPontos = new int[0];
        yPontos = new int[0];
        numeroPontos = 0;
        setCor(Color.black);
    }

    public Polilinha(int x1, int y1, int[] x2, int[] y2, Color novaCor) {
        super(x1,y1, novaCor);
        xPontos = x2 != null ? x2 : new int[0];  // vai verificar se o array passado é nulo, e caso não seja, será usado
        yPontos = y2 != null ? y2 : new int[0];
        numeroPontos = xPontos.length;
    }

    public void desenha(Color corDesenho, Graphics g) {
        g.setColor(corDesenho);
        g.drawPolyline(xPontos, yPontos,   // lista de coordenadas (x,y)
                numeroPontos);  // quantidade de pontos que estão guardados na lista de pontos
    }

    public String transformaString(int valor, int quantasPosicoes) {
        String cadeia = new String(valor + "");
        while (cadeia.length() < quantasPosicoes)
            cadeia = "0" + cadeia;
        return cadeia.substring(0, quantasPosicoes);
    }

    public String transformaString(String valor, int quantasPosicoes) {
        String cadeia = new String(valor + "");
        while (cadeia.length() < quantasPosicoes)
            cadeia = cadeia + " ";
        return cadeia.substring(0, quantasPosicoes);
    }


    public String toString() {
            String resultado = transformaString("p", 5) +
                    transformaString(getX(), 5) +
                    transformaString(getY(), 5) +
                    transformaString(getCor().getRed(), 5) +
                    transformaString(getCor().getGreen(), 5) +
                    transformaString(getCor().getBlue(), 5) +
                    transformaString(numeroPontos, 5);

            for (int i = 0; i < numeroPontos; i++) {
                resultado += transformaString(xPontos[i], 5);
                resultado += transformaString(yPontos[i], 5);
            }

            return resultado;
        }
    }


