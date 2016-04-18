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
 * Класс  описывает монстра и его движение и отвечает за его отображение
 * @author pixxx
 */
public class Enemy extends Pane{
  ImageView imageView;


  /** Положение монстра */
  public double posX;
  public double posY; 
  int Health = 100;

  final int width = 145;
  final int height = 100;
  final int offsetX = 0;
  final int offsetY = 330;
  final int count = 6;
  final int columns = 6;
  final int duration = 700;
  SpriteAnimation animation;
  /**
   * Создает монстра с заданными параметрами
   * @param posX - Начальная позиция по X
   * @param posY - Начальная позиция по Y
   * @throws IOException 
   */
  public Enemy(int posX, int posY) throws IOException{
    InputStream is = Files.newInputStream(Paths.get("res/images/hero_sprites.png"));
    Image img = new Image(is);
    is.close();
    this.imageView = new ImageView(img);
    this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    animation = new SpriteAnimation
        (imageView, Duration.millis(duration), count, columns, offsetX, offsetY, width, height);
    animation.setCycleCount(Animation.INDEFINITE);
    animation.play();
    this.posX = posX;
    this.posY = posY;
    this.setTranslateX(posX);
    this.setTranslateY(posY);
    getChildren().add(imageView);
    GameWindow.gameRoot.getChildren().add(this);
  }

  /**
   * Метод, отвечающий за передвижение монстра по X
   * @param x - На сколько сдвигать монстра
   */
  public void moveX(double x){
    boolean right = true;
    if (x<0) right = false;
    for (int i = 0; i<Math.abs(x); i++){
      if (right){
        this.setTranslateX(this.getTranslateX() + 1);
        posX += 1;
      }
      else {
        this.setTranslateX(this.getTranslateX() - 1);
        posX -= 1;
      }
    }
  }

  /**
   * Метод отвечает за получение урона от вышек
   * @param Damage - Кол-во полученного урона
   */
  public void GetDamage(int Damage){
    Health = Health - Damage;
    if (Health <= 0){
      this.setVisible(false);
      GameWindow.gameRoot.getChildren().remove(this);
      this.animation.stop();
    }
  }
}