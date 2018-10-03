package ordena;

import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Ordena extends Application {

    Botao[] botao = new Botao[11]; // 10 + 1 botoes

    @Override
    public void start(Stage palco) {
        Double margem = 5.0, alturaBotao = 50.0, distanciaEntreElementos = 60.0, altura = 300.0, c = 0.15;

        for (int i = 0; i < 10; i++) {
            botao[i] = new Botao(
                    ("" + new Random().nextInt(10)),
                    alturaBotao,
                    alturaBotao,
                    margem + (i * distanciaEntreElementos),
                    margem,
                    alturaBotao * 0.6
            );
        }

        botao[10] = new Botao("Itera", 3 * alturaBotao, alturaBotao, margem, altura - (altura * 1.5 * c) - margem, 20.0);
        botao[10].setDefaultButton(true);
        botao[10].setOnAction(onClick -> {
            for (int i = 1; i < 10; i++) {
                if (botao[i].menorQue(botao[i - 1])) {
                    troca(i, i - 1);
                    break;
                }
            }
        });

        palco.initStyle(StageStyle.UNDECORATED);
        palco.setScene(new Scene(new Group(botao), 615, altura));
        palco.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean emFoco) {
                if (!emFoco) {
                    Platform.exit();
                }
            }
        });
        palco.show();
    }

    private void troca(int atu, int ant) {
        double deltaX = botao[ant].getTranslateX() - botao[atu].getTranslateX();
        ParallelTransition pt = new ParallelTransition(
                new PathTransition(
                        Duration.seconds(0.5),
                        new Path(
                                botao[ant].moveTo(),
                                botao[ant].lineTo(botao[ant].getTranslateX(), botao[ant].getTranslateY() + 100),
                                botao[ant].lineTo(botao[ant].getTranslateX() - deltaX, botao[ant].getTranslateY()),
                                botao[ant].lineTo(botao[ant].getTranslateX(), botao[ant].getTranslateY() - 100)
                        ),
                        botao[ant]
                ),
                new PathTransition(
                        Duration.seconds(0.5),
                        new Path(
                                botao[atu].moveTo(),
                                botao[atu].lineTo(botao[atu].getTranslateX(), botao[atu].getTranslateY() + 100),
                                botao[atu].lineTo(botao[atu].getTranslateX() + deltaX, botao[atu].getTranslateY()),
                                botao[atu].lineTo(botao[atu].getTranslateX(), botao[atu].getTranslateY() - 100)
                        ),
                        botao[atu]
                )
        );
        pt.play();

        Botao auxiliar = botao[atu];
        botao[atu] = botao[ant];
        botao[ant] = auxiliar;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
