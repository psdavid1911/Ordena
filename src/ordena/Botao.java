package ordena;

import javafx.scene.control.Button;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

public class Botao extends Button implements Comparable<Botao> {

    Double largura, altura;

    public Botao(String texto, Double largura, Double altura, Double x, Double y, Double fonte) {
        setText(texto);
        setPrefSize(largura, altura);
        setTranslateX(x);
        setTranslateY(y);
        setStyle("-fx-font-size: " + fonte + ";");
        this.largura = largura;
        this.altura = altura;
    }

    public void setTranslateXY(Double x, Double y) {
        setTranslateX(x);
        setTranslateY(y);
    }

    @Override
    public int compareTo(Botao o) {
        return getText().compareTo(o.getText());
    }

    public boolean menorQue(Botao o) {
        return this.compareTo(o) < 0;
    }

    public MoveTo moveTo() {
        setTranslateXY(getTranslateX() + (largura / 2) + 3.1, getTranslateY() + (altura / 2) + 7.5);
        return new MoveTo(getTranslateX(), getTranslateY());
    }

    public LineTo lineTo(Double x, Double y) {
        setTranslateXY(x, y);
        return new LineTo(x, y);
    }
}
