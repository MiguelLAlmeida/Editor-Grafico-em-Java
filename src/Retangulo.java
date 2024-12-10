import java.awt.*;
public class Retangulo extends Ponto {

    // herda o ponto de origem (x, y) da classe Ponto

    int largura, altura;
    Color cor;

    public void desenha(Color corDesenho, Graphics g) {
        g.setColor(corDesenho);
        g.drawRect(super.getX(), super.getY(), largura, altura);
        // ponto de origem pega as coordenadas x e y da classe Ponto, enquanto
        // a altura e a largura são passadas pelo usuário.
    }

    public Retangulo() {
        super();
        setAltura(0);
        setLargura(0);
        setCor(Color.black);
    }

    public void setLargura(int novaLargura) {
        largura = novaLargura;
    }

    public void setAltura(int novaAltura) {
        altura = novaAltura;
    }

    public void setCor(Color novaCor) {
        cor = novaCor;
    }

    public Color getCor(){
        return cor;
    }

    public Retangulo(int xOrigem, int yOrigem, int novaLargura, int novaAltura, Color novaCor) {
        super(xOrigem, yOrigem, novaCor);  // construtor de Ponto(x,y)
        setLargura(novaLargura);
        setAltura(novaAltura);
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
        return transformaString("r", 5) +
                transformaString(getX(), 5) +
                transformaString(getY(), 5) +
                transformaString(getCor().getRed(), 5) +
                transformaString(getCor().getGreen(), 5) +
                transformaString(getCor().getBlue(), 5) +
                transformaString(largura, 5) +
                transformaString(altura, 5);
    }
}



