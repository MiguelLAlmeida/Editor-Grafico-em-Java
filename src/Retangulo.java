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
}



