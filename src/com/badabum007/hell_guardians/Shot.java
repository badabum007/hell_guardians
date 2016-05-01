package com.badabum007.hell_guardians;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Tower shot class
 * 
 * @author badabum007
 */
public class Shot {

  ImageView imageView;

  Enemy target;
  public static int damage;

  /** start coordinates */
  double startX;
  double startY;

  double radius = 5;
  double duration = 200;

  final int offsetX = 825;
  final int offsetY = 200;
  final int width = 25;
  final int height = 70;
  final double rotationDegree = -90;
  final int updateTimer = 1;
  final int stepSize = 15;
  Enemy targetEnemy;

  /** shot path */
  Path shotPath;
  PathTransition animation;

  /**
   * generate a shot (circle)
   * 
   * @param target - shot target
   * @param startX - start X coordinate
   * @param startY - start Y coordinate
   */
  public Shot(Enemy target, double startX, double startY) {

    InputStream is;
    try {
      is = Files.newInputStream(Paths.get("res/images/sarcher_sprites.png"));
      Image img = new Image(is);
      is.close();
      imageView = new ImageView(img);
      imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
      imageView.setRotate(rotationDegree);
      imageView.setTranslateX(startX);
      imageView.setTranslateY(startY - GameWindow.blockSize / 2);
      GameWindow.gameRoot.getChildren().add(imageView);

      targetEnemy = target;

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public int update() {
    imageView.setTranslateX(imageView.getTranslateX() + stepSize);
    if (imageView.getTranslateX() > targetEnemy.posX) {
      targetEnemy.getDamage(damage);
      imageView.setVisible(false);
      GameWindow.gameRoot.getChildren().remove(imageView);
      GameWindow.gameRoot.getChildren().remove(this);
      return -1;
    }
    return 0;
  }
}
