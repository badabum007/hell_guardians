package com.badabum007.hell_guardians;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Класс, описывающий вышку
 * @author pixxx
 */
public class Tower extends Pane {
  ImageView imageView;

  /** Позиция вышки, радиус атаки, ее размеры и производимый выстрел */
  double posX;
  double posY;

  SpriteAnimation animation;

  final int COLUMNS  =  8;
  final int COUNT    =  8;
  final int OFFSET_X =  0;
  final int OFFSET_Y =  535;
  final int WIDTH    = 110;
  final int HEIGHT   = 125;
  final int DURATION = 1000;

  /**
   * Метод, создающий объект вышки с заданными параметрами
   * @param posX - Местоположение по X
   * @param posY - Местоположение по Y
   * @param attackRange - Радиус атаки
   * @throws IOException 
   */
  public Tower(double x, double y) throws IOException{
    InputStream is = Files.newInputStream(Paths.get("res/images/sarcher_sprites.png"));
    Image img = new Image(is);
    is.close();
    this.imageView = new ImageView(img);

    this.posX = x;
    this.posY = y;
    this.setTranslateX(posX);
    this.setTranslateY(posY);
    imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
    Animation animation = new SpriteAnimation(
        imageView,
        Duration.millis(DURATION),
        COUNT, COLUMNS,
        OFFSET_X, OFFSET_Y,
        WIDTH, HEIGHT
        );
    animation.setCycleCount(Animation.INDEFINITE);
    animation.play();
    getChildren().add(imageView);
    GameWindow.gameRoot.getChildren().add(this);

    getChildren().add(imageView);
    GameWindow.gameRoot.getChildren().add(this);
  }
}
